package com.cockerspaniel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Staking(
    val address: String,
    val delegationTotal: BigDecimal
) : Parcelable
