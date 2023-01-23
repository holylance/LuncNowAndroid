package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.Amount

internal data class AmountResponse(
    val denom: String,
    val amount: String,
) {

    internal fun toAmount() = Amount(denom, amount)
}
