package com.example.empyreancodeassessment.network.models

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("timestamp")
    val timestamp: String
)