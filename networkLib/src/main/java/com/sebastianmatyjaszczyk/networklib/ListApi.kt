package com.sebastianmatyjaszczyk.networklib

import com.sebastianmatyjaszczyk.networklib.response.NetworkEntity
import retrofit2.http.GET

interface ListApi {

    @GET("recruitment-task")
    suspend fun get(): List<NetworkEntity>
}