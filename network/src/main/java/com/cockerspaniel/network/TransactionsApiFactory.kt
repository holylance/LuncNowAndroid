package com.cockerspaniel.network

import com.cockerspaniel.network.base.internal.RetrofitService
import com.cockerspaniel.network.internal.TransactionsApiService
import com.cockerspaniel.network.internal.TransactionsServiceImpl

object TransactionsApiFactory {

    fun create(): TransactionsService {
        return TransactionsServiceImpl(TransactionsApiService.create(RetrofitService().retrofit))
    }
}
