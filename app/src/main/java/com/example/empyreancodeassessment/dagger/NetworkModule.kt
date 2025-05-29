package com.example.empyreancodeassessment.dagger

import com.example.empyreancodeassessment.network.MockAPIService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideMockAPIService(): MockAPIService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit
            .Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://10.0.2.2:3000")
            .build()

        return retrofit.create(MockAPIService::class.java)
    }
}