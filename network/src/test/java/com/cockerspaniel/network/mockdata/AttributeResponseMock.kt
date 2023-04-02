package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.AttributeResponse

internal object AttributeResponseMock {

    fun generate(): AttributeResponse {
        return AttributeResponse(
            key = "key",
            value = "value"
        )
    }
}
