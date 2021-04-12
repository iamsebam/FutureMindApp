package com.sebastianmatyjaszczyk.networklib.response

import com.google.gson.annotations.SerializedName

data class NetworkEntity(
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("modificationDate")
    val modificationDate: String
)
