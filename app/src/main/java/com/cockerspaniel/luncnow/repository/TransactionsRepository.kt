package com.cockerspaniel.luncnow.repository

import com.cockerspaniel.network.TerraClassicService
import com.cockerspaniel.network.model.TransactionList
import io.reactivex.rxjava3.core.Single

class TransactionsRepository(
    private val terraClassicService: TerraClassicService
) {

    fun fetchTransactions(): Single<TransactionList> {
        return terraClassicService.getTransactions()
    }
}
