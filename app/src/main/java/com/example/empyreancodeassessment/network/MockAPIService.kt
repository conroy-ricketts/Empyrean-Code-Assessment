package com.example.empyreancodeassessment.network

import com.example.empyreancodeassessment.network.models.CommentResponse
import com.example.empyreancodeassessment.network.models.ItemResponse
import com.example.empyreancodeassessment.network.models.LoginRequest
import com.example.empyreancodeassessment.network.models.LoginResponse
import com.example.empyreancodeassessment.network.models.UserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MockAPIService {
    @POST("login")
    fun postLogin(
        @Body loginRequest: LoginRequest
    ): Single<LoginResponse>

    @GET("items")
    fun getItems(
        @Header("Authorization") authToken: String
    ): Single<List<ItemResponse>>

    @GET("items/{id}")
    fun getItem(
        @Header("Authorization") authToken: String,
        @Path("id") itemId: Int
    ): Single<ItemResponse>

    @GET("items/{id}/comments")
    fun getItemComments(
        @Header("Authorization") authToken: String,
        @Path("id") itemId: Int
    ): Single<List<CommentResponse>>

    @GET("users")
    fun getUsers(
        @Header("Authorization") authToken: String
    ): Single<List<UserResponse>>

    @GET("users/{id}")
    fun getUser(
        @Header("Authorization") authToken: String,
        @Path("id") userId: Int
    ): Single<UserResponse>
}