package com.cockerspaniel.network.internal

import com.cockerspaniel.network.ProductService
import com.cockerspaniel.network.model.Product
import io.reactivex.rxjava3.core.Single

internal class ProductServiceImpl(
    private val productApiService: ProductApiService
) : ProductService {

    override fun getProducts(token: String): Single<List<Product>> {
        return productApiService.getProducts(token)
            .map { it.map { response -> response.toProduct() } }
    }
}