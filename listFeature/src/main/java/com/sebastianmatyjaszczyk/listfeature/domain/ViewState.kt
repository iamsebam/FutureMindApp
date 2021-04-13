package com.sebastianmatyjaszczyk.listfeature.domain

sealed class ViewState {

    data class Success(
        val data: ViewEntity
    ) : ViewState()

    class Error(
        val error: Throwable
    ) : ViewState()
}
