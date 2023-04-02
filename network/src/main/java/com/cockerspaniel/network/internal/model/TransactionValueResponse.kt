package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.TransactionValue

internal data class TransactionValueResponse(
    val memo: String
) {

    fun toTransactionValue() = TransactionValue(memo = memo)
}
