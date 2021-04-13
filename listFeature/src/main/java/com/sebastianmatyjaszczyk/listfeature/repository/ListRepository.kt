package com.sebastianmatyjaszczyk.listfeature.repository

import com.sebastianmatyjaszczyk.listfeature.domain.ListResult
import com.sebastianmatyjaszczyk.listfeature.domain.sorted
import com.sebastianmatyjaszczyk.networklib.response.NetworkResponse
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
                when (val result = remoteSource.fetchList()) {
                    is NetworkResponse.Success -> {
                        val mappedResult = networkEntityMapper.mapToViewEntity(result.data).sorted()
                        ListResult.Success(mappedResult)
                    }
                    is NetworkResponse.Failure -> ListResult.Error(result.error)
                }
            } catch (error: Throwable) {
                ListResult.Error(error)
            }
        }
}
