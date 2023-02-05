package com.cockerspaniel.luncnow.usecase

import com.cockerspaniel.luncnow.repository.StakingRepository
import com.cockerspaniel.network.model.Staking
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import java.math.BigDecimal

class StakingUseCase(
    private val stakingRepository: StakingRepository
) {
    fun fetchStaking(listAddress: List<String>): Single<List<Staking>> {
        val temp = listAddress.map { Staking(it, BigDecimal.ZERO) }
        return Single.just(temp)
            .compose(getStaking())
    }

    private fun getStaking(): SingleTransformer<List<Staking>, List<Staking>> {
        return SingleTransformer { single ->
            single.flatMap { list ->
                Single.zip(
                    list.map { staking ->
                        stakingRepository.fetchStaking(staking.address)
                            .map {
                                staking.copy(delegationTotal = it.delegationTotal)
                            }
                    }
                ) {
                    it.toList() as List<Staking>
                }
            }
        }
    }
}
