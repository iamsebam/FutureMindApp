package com.sebastianmatyjaszczyk.listfeature.repository

import com.sebastianmatyjaszczyk.listfeature.domain.ListResult
import com.sebastianmatyjaszczyk.listfeature.domain.sorted
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ListRepository @Inject constructor(
    private val remoteSource: ListDataProvider,
    private val networkEntityMapper: NetworkEntityMapper
) {

    suspend fun loadData(): ListResult = fetchDataFromRemote()

    private suspend fun fetchDataFromRemote() =
        withContext(Dispatchers.IO) {
            try {
                val result = remoteSource.fetchList()
                val mappedResult = networkEntityMapper.mapToViewEntity(result).sorted()
                ListResult.Success(mappedResult)
            } catch (e: Throwable) {
                ListResult.Error(e)
            }
        }
}
