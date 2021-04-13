package com.sebastianmatyjaszczyk.networklib.response

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (Call::class.java!=getRawType(returnType)) return null

        check(returnType is ParameterizedType) { "Return type must be a parameterized type" }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType)!=NetworkResponse::class.java) return null

        check(responseType is ParameterizedType) { "Response type must be a parameterized type." }

        val errorType = getParameterUpperBound(1, responseType)
        if (getRawType(errorType)!=NetworkError::class.java) return null

        val successType = getParameterUpperBound(0, responseType)
        return NetworkResponseAdapter<Any>(successType)
    }
}