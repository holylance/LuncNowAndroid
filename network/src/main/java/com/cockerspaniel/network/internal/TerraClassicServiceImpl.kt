package com.cockerspaniel.network.internal

import com.cockerspaniel.network.TerraClassicService
import com.cockerspaniel.network.model.TransactionList
import io.reactivex.rxjava3.core.Single

internal class TerraClassicServiceImpl(
    private val terraClassicApiService: TerraClassicApiService
) : TerraClassicService {

    override fun getTransactions(): Single<TransactionList> {
        return terraClassicApiService.getTransactions(BURN_WALLET, LIMIT)
            .map { it.toTransactionList() }
    }

    companion object {
        const val BURN_WALLET = "terra1sk06e3dyexuq4shw77y3dsv480xv42mq73anxu"
        const val LIMIT = 100
    }
}