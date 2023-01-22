package com.cockerspaniel.network

import com.cockerspaniel.network.model.TransactionList
import io.reactivex.rxjava3.core.Single

/**
 * ProductService to call respective product endpoints
 */
interface TransactionsService {
    /**
     * Returns a list of product
     *
     * @return list of product or error
     */
    fun getTransactions(): Single<TransactionList>
}
