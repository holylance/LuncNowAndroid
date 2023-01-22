package com.cockerspaniel.network.internal.model

import android.os.Parcelable
import com.cockerspaniel.network.model.Amount
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AmountResponse(
    val denom: String,
    val amount: String,
) : Parcelable {

    fun toAmount() = Amount(denom, amount)
}
