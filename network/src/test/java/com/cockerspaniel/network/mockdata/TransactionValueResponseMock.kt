package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.TransactionValueResponse

internal object TransactionValueResponseMock {

    fun generate(): TransactionValueResponse {
        return TransactionValueResponse(
            memo = "memo"
        )
    }
}
