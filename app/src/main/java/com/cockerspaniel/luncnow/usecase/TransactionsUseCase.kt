package com.cockerspaniel.luncnow.usecase

import com.cockerspaniel.network.model.TransactionList
import com.cockerspaniel.luncnow.repository.TransactionsRepository
import io.reactivex.rxjava3.core.Single

class TransactionsUseCase(
    private val transactionsRepository: TransactionsRepository
) {

    fun fetchTransactions(): Single<TransactionList> {
        return transactionsRepository.fetchTransactions()
    }
}
