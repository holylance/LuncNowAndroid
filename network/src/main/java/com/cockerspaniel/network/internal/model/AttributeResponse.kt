package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.Attribute

internal data class AttributeResponse(
    val key: String,
    val value: String,
) {

    fun toAttribute() = Attribute(key, value)
}
