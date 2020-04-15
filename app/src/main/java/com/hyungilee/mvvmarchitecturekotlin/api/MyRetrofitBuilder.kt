package com.hyungilee.mvvmarchitecturekotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder {

    const val BASE_URL = "https://open-api.xyz/"

    // Singleton Retrofit builder
    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            // 서버로부터 호출되는 데이터의 형식은 JSON형태이므로, 이를 Convert할 Converter가 필요하다.
            // Gson Converter = JSON objcet -> Java object
    }

    // Singleton apiService (Network request)
    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

}