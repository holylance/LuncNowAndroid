package com.cockerspaniel.network

import com.cockerspaniel.network.model.Product
import io.reactivex.rxjava3.core.Single

/**
 * ProductService to call respective product endpoints
 */
interface ProductService {
    /**
     * Returns a list of product
     *
     * @return list of product or error
     */
    fun getProducts(token: String): Single<List<Product>>
}
