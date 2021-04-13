package com.sebastianmatyjaszczyk.listfeature.repository

import com.sebastianmatyjaszczyk.networklib.listApi.ListApi
import javax.inject.Inject

class ListDataProvider @Inject constructor(
    private val listApi: ListApi
) {

    suspend fun fetchList() = listApi.get()
}
