package com.sebastianmatyjaszczyk.commonlib

sealed class NetworkError : RuntimeException() {

    object NoNetworkError : NetworkError()

    data class HttpError(val code: Int, val body: String) : NetworkError()

    data class UnknownError(val throwable: Throwable) : NetworkError()
}
