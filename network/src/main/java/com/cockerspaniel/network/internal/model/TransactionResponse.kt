package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.base.util.toZonedDateTime
import com.cockerspaniel.network.model.Transaction

internal data class TransactionResponse(
    val tx: TransactionInfoResponse,
    val logs: List<LogsResponse>,
    val timestamp: String
) {

    internal fun toTransactionInfo() = Transaction(
        tx = tx.toTransactionInfo(),
        logs = logs.map { it.toLogs() },
        timestamp = timestamp.toZonedDateTime()
    )
}
