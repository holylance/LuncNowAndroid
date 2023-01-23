package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.TransactionInfo

internal data class TransactionInfoResponse(
    val tx: TransactionResponse
) {

    internal fun toTransactionInfo() = TransactionInfo(
        tx = tx.toTransaction()
    )
}
