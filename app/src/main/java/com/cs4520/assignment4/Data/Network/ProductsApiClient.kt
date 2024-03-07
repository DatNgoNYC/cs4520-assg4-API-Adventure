package com.cs4520.assignment4.Data.Network

import com.cs4520.assignment4.Data.Entities.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object RetrofitClient {
    const val BASE_URL: String = "https://kgtttq6tg9.execute-api.us-east-2.amazonaws.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    object ProductsApiService {
        val amazonApi: amazonApiService by lazy {
            RetrofitClient.retrofit.create(amazonApiService::class.java)
        }
    }
}

interface amazonApiService {
    @GET("prod/?page={id}")
    suspend fun getProductListByPage(pageNumber: Int): Call<List<Product>>
}

