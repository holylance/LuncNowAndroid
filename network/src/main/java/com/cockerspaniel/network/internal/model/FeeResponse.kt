package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.Fee

internal data class FeeResponse(
    val amount: List<AmountResponse>
) {

    internal fun toFee() = Fee(amount.map { it.toAmount() })
}
