package com.cockerspaniel.network

import com.cockerspaniel.network.base.internal.RetrofitService
import com.cockerspaniel.network.internal.TerraClassicApiService
import com.cockerspaniel.network.internal.TerraClassicServiceImpl

object TransactionsApiFactory {

    fun create(): TerraClassicService {
        return TerraClassicServiceImpl(TerraClassicApiService.create(RetrofitService().retrofit))
    }
}
