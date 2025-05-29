package com.example.empyreancodeassessment.network.models

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("image")
    val image: String
)