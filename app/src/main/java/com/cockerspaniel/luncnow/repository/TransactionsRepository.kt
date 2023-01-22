package com.cockerspaniel.luncnow.repository

import com.cockerspaniel.network.TransactionsService
import com.cockerspaniel.network.model.TransactionList
import io.reactivex.rxjava3.core.Single

class TransactionsRepository(
    private val transactionsService: TransactionsService
) {

    fun fetchTransactions(): Single<TransactionList> {
        return transactionsService.getTransactions()
    }
}
