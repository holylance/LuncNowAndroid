package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.Transaction

internal data class TransactionResponse(
    val tx: TransactionInfoResponse,
    val logs: List<LogsResponse>
) {

    internal fun toTransactionInfo() = Transaction(
        tx = tx.toTransactionInfo(),
        logs = logs.map { it.toLogs() }
    )
}
