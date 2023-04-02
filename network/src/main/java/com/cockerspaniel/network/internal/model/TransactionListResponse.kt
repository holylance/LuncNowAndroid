package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.TransactionList

internal data class TransactionListResponse(
    val limit: Int,
    val txs: List<TransactionResponse>
) {

    fun toTransactionList() = TransactionList(
        limit = limit,
        txs = txs.map { it.toTransactionInfo() }
    )
}
