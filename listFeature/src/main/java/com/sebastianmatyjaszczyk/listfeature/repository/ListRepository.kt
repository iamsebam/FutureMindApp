package com.sebastianmatyjaszczyk.listfeature.repository

import com.sebastianmatyjaszczyk.commonlib.Result
import com.sebastianmatyjaszczyk.databaselib.dao.ListItemDao
import com.sebastianmatyjaszczyk.databaselib.entity.ListItemEntity
import com.sebastianmatyjaszczyk.listfeature.util.NetworkEntityMapper
import com.sebastianmatyjaszczyk.networklib.listApi.ListApi
import com.sebastianmatyjaszczyk.networklib.response.NetworkResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
@ExperimentalCoroutinesApi
class ListRepository @Inject constructor(
    private val dao: ListItemDao,
    private val remoteSource: ListApi,
    private val networkEntityMapper: NetworkEntityMapper
) {

    private val _listItemsFlow: MutableStateFlow<Result<List<ListItemEntity>>> = MutableStateFlow(Result.Loading)

    val listItemsFlow: StateFlow<Result<List<ListItemEntity>>> = _listItemsFlow.asStateFlow()

    suspend fun loadData() {
        _listItemsFlow.value = Result.Loading
        _listItemsFlow.value = fetchFromCache().takeIf { it.isNotEmpty() }
            ?: fetchFromRemoteAndCache()
    }

    suspend fun refreshData() {
        _listItemsFlow.value = Result.Loading
        _listItemsFlow.value = fetchFromRemoteAndCache()
    }

    private suspend fun fetchFromRemoteAndCache() =
        fetchData().also { result ->
            if (result is Result.Success) cacheData(result.data)
        }

    private suspend fun fetchData() =
        withContext(Dispatchers.IO) {
            try {
                when (val result = remoteSource.get()) {
                    is NetworkResponse.Success -> {
                        val domainListItems = networkEntityMapper.mapToDomainEntities(result.data)
                        emptyOrSuccess(domainListItems)
                    }
                    is NetworkResponse.Failure -> fetchFromCacheOrError(result.error)
                }
            } catch (error: Throwable) {
                fetchFromCacheOrError(error)
            }
        }

    private fun emptyOrSuccess(domainListItems: List<ListItemEntity>) =
        when {
            domainListItems.isEmpty() -> Result.Empty
            else -> Result.Success(domainListItems)
        }

    private suspend fun fetchFromCacheOrError(error: Throwable) =
        fetchFromCache().takeIf { it.isNotEmpty() } ?: Result.Failure(error)

    private suspend fun fetchFromCache() =
        withContext(Dispatchers.IO) {
            val result = dao.getAllSorted()
            Result.Success(result).takeIf { result.isNotEmpty() } ?: Result.Empty
        }

    private suspend fun cacheData(listItems: List<ListItemEntity>) =
        withContext(Dispatchers.IO) {
            dao.updateAll(listItems)
        }
}
