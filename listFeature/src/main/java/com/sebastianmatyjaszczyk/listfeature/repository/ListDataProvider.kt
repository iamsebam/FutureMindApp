package com.sebastianmatyjaszczyk.listfeature.repository

import com.sebastianmatyjaszczyk.networklib.ListApi
import javax.inject.Inject

class ListDataProvider @Inject constructor(
    private val listApi: ListApi
) {

    suspend fun fetchList() = listApi.get()
}
