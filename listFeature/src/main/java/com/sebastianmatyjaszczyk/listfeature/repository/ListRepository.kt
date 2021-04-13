package com.sebastianmatyjaszczyk.listfeature.repository

import com.sebastianmatyjaszczyk.databaselib.dao.ListItemDao
import com.sebastianmatyjaszczyk.databaselib.entity.ListItemEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ViewState
import com.sebastianmatyjaszczyk.listfeature.domain.sorted
import com.sebastianmatyjaszczyk.networklib.response.NetworkResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ListRepository @Inject constructor(
    private val dao: ListItemDao,
    private val remoteSource: ListDataProvider,
    private val networkEntityMapper: NetworkEntityMapper,
    private val databaseEntityMapper: DatabaseEntityMapper
) {

    suspend fun loadData(): ViewState = withContext(Dispatchers.IO) {
        val result = dao.getAllSorted()

        if (result.isNotEmpty()) {
            val mappedResult = databaseEntityMapper.mapToViewEntity(result)
            ViewState.Success(mappedResult)
        } else {
            fetchAndCacheDataFromRemote()
        }
    }

    suspend fun refreshData(): ViewState = fetchAndCacheDataFromRemote()

    private suspend fun fetchAndCacheDataFromRemote() =
        withContext(Dispatchers.IO) {
            try {
                when (val result = remoteSource.fetchList()) {
                    is NetworkResponse.Success -> {
                        val viewEntity = networkEntityMapper.mapToViewEntity(result.data).sorted()
                        val databaseListItems = networkEntityMapper.mapToDatabaseEntities(result.data)
                        cacheData(databaseListItems)
                        ViewState.Success(viewEntity)
                    }
                    is NetworkResponse.Failure -> ViewState.Error(result.error)
                }
            } catch (error: Throwable) {
                ViewState.Error(error)
            }
        }

    private suspend fun cacheData(databaseListItems: List<ListItemEntity>) =
        withContext(Dispatchers.IO) {
            dao.updateAll(databaseListItems)
        }
}
