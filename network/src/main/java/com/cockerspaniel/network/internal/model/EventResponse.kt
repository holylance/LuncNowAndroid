package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.Event

internal data class EventResponse(
    val type: String,
    val attributes: List<AttributeResponse>
) {

    fun toEvent() = Event(
        type = type,
        attributes = attributes.map { it.toAttribute() }
    )
}
