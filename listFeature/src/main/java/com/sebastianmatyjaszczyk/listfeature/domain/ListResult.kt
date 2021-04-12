package com.sebastianmatyjaszczyk.listfeature.domain

sealed class ListResult {

    data class Success(
        val data: ViewEntity
    ) : ListResult()

    class Error(
        val error: Throwable
    ) : ListResult()
}
