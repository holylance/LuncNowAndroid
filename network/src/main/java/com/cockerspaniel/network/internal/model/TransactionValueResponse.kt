package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.TransactionValue

internal data class TransactionValueResponse(
    val memo: String
) {

    internal fun toTransactionValue() = TransactionValue(memo = memo)
}
