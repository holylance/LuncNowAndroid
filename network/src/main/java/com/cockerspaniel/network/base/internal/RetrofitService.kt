package com.cockerspaniel.network.base.internal

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitService(
    private val gson: Gson = GsonBuilder().create()
) {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(httpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val httpClient = OkHttpClient.Builder().build()

    companion object {
        private const val apiUrl = "https://columbus-fcd.terra.dev/v1/"
    }
}
