package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.TransactionInfo

internal data class TransactionInfoResponse(
    val value: TransactionValueResponse
) {

    fun toTransactionInfo() = TransactionInfo(value = value.toTransactionValue())
}
