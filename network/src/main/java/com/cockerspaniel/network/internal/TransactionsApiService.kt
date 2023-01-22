package com.cockerspaniel.network.internal

import com.cockerspaniel.network.internal.model.TransactionListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

internal interface TransactionsApiService {

    @GET("v1/txs")
    fun getTransactions(
        @Path("account")
        account: String,
        @Field("limit")
        limit: Int
    ): Single<TransactionListResponse>

    companion object {
        fun create(retrofit: Retrofit): TransactionsApiService {
            return retrofit.create(TransactionsApiService::class.java)
        }
    }
}
