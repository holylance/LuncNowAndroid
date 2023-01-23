package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.Transaction

internal data class TransactionResponse(
    val value: TransactionValueResponse
) {

    internal fun toTransaction() = Transaction(
        value = value.toTransactionValue()
    )
}
