package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.LogsResponse

internal object LogsResponseMock {

    fun generate(): LogsResponse {
        return LogsResponse(
            events = listOf(EventResponseMock.generate())
        )
    }
}
