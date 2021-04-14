package com.sebastianmatyjaszczyk.listfeature.repository

import com.sebastianmatyjaszczyk.databaselib.dao.ListItemDao
import com.sebastianmatyjaszczyk.databaselib.entity.ListItemEntity
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
    private val remoteSource: ListDataProvider,
    private val networkEntityMapper: NetworkEntityMapper,
) {

    private val _listItemFlow: MutableStateFlow<Result<List<ListItemEntity>>> = MutableStateFlow(Result.Loading)

    val listItemsFlow: StateFlow<Result<List<ListItemEntity>>> = _listItemFlow.asStateFlow()

    suspend fun refresh() {
        _listItemFlow.value = fetchAndCacheDataFromRemote()
    }

    private suspend fun fetchAndCacheDataFromRemote(): Result<List<ListItemEntity>> = withContext(Dispatchers.IO) {
        try {
            Result.Loading
            when (val result = remoteSource.fetchList()) {
                is NetworkResponse.Success -> {
                    val domainListItems = networkEntityMapper.mapToDomainEntities(result.data)
                    emptyOrSuccessAndCache(domainListItems)
                }
                is NetworkResponse.Failure -> fetchFromCacheOrError(result.error)
            }
        } catch (error: Throwable) {
            fetchFromCacheOrError(error)
        }
    }

    private suspend fun emptyOrSuccessAndCache(domainListItems: List<ListItemEntity>) =
        when {
            domainListItems.isEmpty() -> Result.Empty
            else -> {
                cacheData(domainListItems)
                Result.Success(domainListItems)
            }
        }

    private suspend fun fetchFromCacheOrError(error: Throwable) =
        fetchFromCache().takeIf { it.isNotEmpty() } ?: Result.Failure(error)

    private suspend fun fetchFromCache(): Result<List<ListItemEntity>> = withContext(Dispatchers.IO) {
        val result = dao.getAllSorted()
        Result.Success(result).takeIf { result.isNotEmpty() } ?: Result.Empty
    }

    private suspend fun cacheData(listItems: List<ListItemEntity>) =
        withContext(Dispatchers.IO) {
            dao.updateAll(listItems)
        }
}
