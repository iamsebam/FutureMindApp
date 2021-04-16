package com.sebastianmatyjaszczyk.networklib.response

import com.sebastianmatyjaszczyk.commonlib.NetworkError
import com.sebastianmatyjaszczyk.commonlib.NetworkResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResponseAdapter<S>(
    private val successType: Type,
) : CallAdapter<S, Call<NetworkResponse<S, NetworkError>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, NetworkError>> {
        return NetworkResponseCall(call, successType)
    }
}
