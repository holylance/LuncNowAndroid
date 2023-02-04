package com.cockerspaniel.network

import com.cockerspaniel.network.model.Staking
import com.cockerspaniel.network.model.TransactionList
import io.reactivex.rxjava3.core.Single

/**
 * TerraClassicService to call respective endpoints
 */
interface TerraClassicService {
    /**
     * Returns a list of transaction
     *
     * @return list of transaction or error
     */
    fun getTransactions(): Single<TransactionList>

    /**
     * Returns a staking
     *
     * @return staking or error
     */
    fun getStaking(address: String): Single<Staking>
}
