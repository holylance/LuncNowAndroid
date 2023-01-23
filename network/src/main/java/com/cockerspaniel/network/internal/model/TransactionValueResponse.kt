package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.TransactionValue

internal data class TransactionValueResponse(
    val fee: FeeResponse,
    val memo: String
) {

    internal fun toTransactionValue() = TransactionValue(
        fee = fee.toFee(),
        memo = memo
    )
}
