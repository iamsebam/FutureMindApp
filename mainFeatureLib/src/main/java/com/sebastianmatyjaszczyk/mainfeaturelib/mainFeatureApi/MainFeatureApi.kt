package com.sebastianmatyjaszczyk.mainfeaturelib.mainFeatureApi

import com.sebastianmatyjaszczyk.commonlib.NetworkError
import com.sebastianmatyjaszczyk.commonlib.NetworkResponse
import retrofit2.http.GET

interface MainFeatureApi {

    @GET("recruitment-task")
    suspend fun get(): NetworkResponse<List<NetworkEntity>, NetworkError>
}
