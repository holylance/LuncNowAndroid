package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.base.util.toZonedDateTime
import com.cockerspaniel.network.model.Transaction

internal data class TransactionResponse(
    val id: Long,
    val tx: TransactionInfoResponse,
    val logs: List<LogsResponse>,
    val timestamp: String
) {

    fun toTransactionInfo() = Transaction(
        id = id,
        tx = tx.toTransactionInfo(),
        logs = logs.map { it.toLogs() },
        timestamp = timestamp.toZonedDateTime()
    )
}
