package com.sebastianmatyjaszczyk.networklib.listApi

import com.sebastianmatyjaszczyk.networklib.response.NetworkError
import com.sebastianmatyjaszczyk.networklib.response.NetworkResponse
import retrofit2.http.GET

interface ListApi {

    @GET("recruitment-task")
    suspend fun get(): NetworkResponse<List<NetworkEntity>, NetworkError>
}
