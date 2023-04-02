package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.TransactionResponse
import java.time.ZonedDateTime

internal object TransactionResponseMock {

    fun generate(): TransactionResponse {
        return TransactionResponse(
            id = 1L,
            tx = TransactionInfoResponseMock.generate(),
            logs = listOf(LogsResponseMock.generate()),
            timestamp = ZonedDateTime.now().toString()
        )
    }
}
