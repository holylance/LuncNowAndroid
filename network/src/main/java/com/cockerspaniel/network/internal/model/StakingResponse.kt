package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.Staking

internal data class StakingResponse(
    val delegationTotal: String
) {

    internal fun toStaking() = Staking(
        delegationTotal = delegationTotal.toBigDecimal()
    )
}
