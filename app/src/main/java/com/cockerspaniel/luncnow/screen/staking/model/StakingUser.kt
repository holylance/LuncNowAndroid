package com.cockerspaniel.luncnow.screen.staking.model

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class StakingUser(
    val address: String,
    val name: String,
    val country: String,
    @SerializedName("country_full")
    val countryFull: String,
    val amount: String? = null,
    val amountNum: BigInteger = BigInteger.ZERO
)
