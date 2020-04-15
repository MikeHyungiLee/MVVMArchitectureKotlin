package com.hyungilee.mvvmarchitecturekotlin.api

import com.hyungilee.mvvmarchitecturekotlin.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/user/{userId}")
    // 여기서 사용되는 suspend fun은 'coroutine'을 사용하기 위한 fun이다.
    // use 'coroutine' fun return the date from network.
    suspend fun getUser(
        @Path("userId") userId: String
    ): User
}