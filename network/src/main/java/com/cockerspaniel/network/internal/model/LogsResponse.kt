package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.Log

internal data class LogsResponse(
    val events: List<EventResponse>
) {

    internal fun toLogs() = Log(
        events = events.map { it.toEvent() }
    )
}
