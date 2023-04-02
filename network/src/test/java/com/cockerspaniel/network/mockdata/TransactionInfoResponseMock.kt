package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.TransactionInfoResponse

internal object TransactionInfoResponseMock {

    fun generate(): TransactionInfoResponse {
        return TransactionInfoResponse(
            value = TransactionValueResponseMock.generate()
        )
    }
}
