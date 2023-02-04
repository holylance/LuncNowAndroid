package com.cockerspaniel.luncnow.usecase

import com.cockerspaniel.luncnow.repository.StakingRepository
import com.cockerspaniel.luncnow.util.rx.SchedulerProvider
import com.cockerspaniel.network.model.Staking
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import java.math.BigDecimal

class StakingUseCase(
    private val stakingRepository: StakingRepository
) {
    fun fetchStaking(address: String): Single<Staking> {
        return stakingRepository.fetchStaking(address)
    }

    private fun getStaking(): SingleTransformer<Staking, Staking> {
        return SingleTransformer { single ->
            single.flatMap { staking ->
                stakingRepository.fetchStaking(staking.address)
                    .map {
                        staking.copy(delegationTotal = it.delegationTotal)
                    }
            }
        }
    }
}
