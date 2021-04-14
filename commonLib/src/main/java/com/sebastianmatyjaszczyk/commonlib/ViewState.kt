package com.sebastianmatyjaszczyk.commonlib

sealed class ViewState<out T> {

    data class Success<out T>(
        val data: T
    ) : ViewState<T>()

    class Error(
        val message: String
    ) : ViewState<Nothing>()

    object Loading : ViewState<Nothing>()
}
