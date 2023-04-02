package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.TransactionListResponse

internal object TransactionListResponseMock {

    fun generate(): TransactionListResponse {
        return TransactionListResponse(
            limit = 100000000,
            txs = listOf(TransactionResponseMock.generate())
        )
    }
}
