package com.cockerspaniel.network.internal

import com.cockerspaniel.network.internal.model.ProductResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.GET

internal interface ProductApiService {

    @GET("products")
    fun getProducts(
        @Field("token")
        loginToken: String
    ): Single<List<ProductResponse>>

    companion object {
        fun create(retrofit: Retrofit): ProductApiService {
            return retrofit.create(ProductApiService::class.java)
        }
    }
}
