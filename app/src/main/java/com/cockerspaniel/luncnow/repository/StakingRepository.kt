package com.cockerspaniel.luncnow.repository

import com.cockerspaniel.network.TerraClassicService
import com.cockerspaniel.network.model.Staking
import io.reactivex.rxjava3.core.Single

class StakingRepository(
    private val terraClassicService: TerraClassicService
) {

    fun fetchStaking(address: String): Single<Staking> {
        return terraClassicService.getStaking(address)
    }
}
