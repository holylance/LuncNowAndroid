package com.cockerspaniel.network

import com.cockerspaniel.network.base.internal.RetrofitService
import com.cockerspaniel.network.internal.ProductApiService
import com.cockerspaniel.network.internal.ProductServiceImpl

object ProductApiFactory {
    fun create(): ProductService {
        return ProductServiceImpl(ProductApiService.create(RetrofitService().retrofit))
    }
}
