package com.sebastianmatyjaszczyk.listfeature.repository

sealed class Result<out S> {

    object Loading : Result<Nothing>()

    object Empty : Result<Nothing>()

    class Failure(val error: Throwable) : Result<Nothing>()

    class Success<out S>(val data: S) : Result<S>()

    fun isNotEmpty() = this !is Empty
}
