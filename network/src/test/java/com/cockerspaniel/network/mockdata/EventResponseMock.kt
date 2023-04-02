package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.EventResponse

internal object EventResponseMock {

    fun generate(): EventResponse {
        return EventResponse(
            type = "type",
            attributes = listOf(AttributeResponseMock.generate())
        )
    }
}
