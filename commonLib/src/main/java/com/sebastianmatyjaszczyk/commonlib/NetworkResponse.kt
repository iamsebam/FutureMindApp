package com.sebastianmatyjaszczyk.commonlib

sealed class NetworkResponse<out T, out E> {

    data class Success<out T>(val data: T) : NetworkResponse<T, Nothing>()

    data class Failure<out E>(val error: E) : NetworkResponse<Nothing, E>()
}
