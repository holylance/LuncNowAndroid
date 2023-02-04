package com.cockerspaniel.network.internal.model

import com.cockerspaniel.network.model.Staking

internal data class StakingResponse(
    val delegationTotal: String
) {

    internal fun toStaking(address: String) = Staking(
        address = address,
        delegationTotal = delegationTotal.toBigDecimal()
    )
}
