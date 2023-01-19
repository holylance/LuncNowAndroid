package com.cockerspaniel.network.internal

import com.cockerspaniel.network.internal.model.LoginRequest
import com.cockerspaniel.network.internal.model.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

internal interface LoginApiService {

    @POST("com.cockerspaniel.luncnow/login")
    fun postLogin(
        @Body requestBody: LoginRequest
    ): Single<LoginResponse>

    companion object {
        fun create(retrofit: Retrofit): LoginApiService {
            return retrofit.create(LoginApiService::class.java)
        }
    }
}
