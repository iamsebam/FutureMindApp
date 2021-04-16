package com.sebastianmatyjaszczyk.networklib.response

import com.sebastianmatyjaszczyk.commonlib.NetworkError
import com.sebastianmatyjaszczyk.commonlib.NetworkResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

// Big kudos for this underrated article: https://proandroiddev.com/retrofit-calladapter-for-either-type-2145781e1c20
internal class NetworkResponseCall<R>(
    private val delegate: Call<R>,
    private val successType: Type,
) : Call<NetworkResponse<R, NetworkError>> {

    override fun enqueue(callback: Callback<NetworkResponse<R, NetworkError>>) =
        delegate.enqueue(object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@NetworkResponseCall, Response.success(response.toNetworkResponse()))
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val error = when (throwable) {
                    is IOException -> NetworkError.NoNetworkError
                    else -> throwable
                }
                val networkResponse = NetworkResponse.Failure(error) as NetworkResponse<R, NetworkError>
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })

    private fun Response<R>.toNetworkResponse(): NetworkResponse<R, NetworkError> {
        if (!isSuccessful) {
            val errorBody = errorBody()?.string() ?: ""
            return NetworkResponse.Failure(NetworkError.HttpError(code(), errorBody))
        }

        body()?.let { body -> return NetworkResponse.Success(body) }

        return if (successType == Unit::class.java) {
            NetworkResponse.Success(Unit) as NetworkResponse<R, NetworkError>
        } else {
            NetworkResponse.Failure(UnknownError("Response body was null")) as NetworkResponse<R, NetworkError>
        }
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone(), successType)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun timeout() = delegate.timeout()

    override fun execute(): Response<NetworkResponse<R, NetworkError>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()
}
