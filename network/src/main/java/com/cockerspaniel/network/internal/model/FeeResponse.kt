package com.cockerspaniel.network.internal.model

import android.os.Parcelable
import com.cockerspaniel.network.model.Amount
import com.cockerspaniel.network.model.Fee
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeeResponse(
    val amount: List<AmountResponse>
) : Parcelable {

    fun toFee() = Fee(amount.map { it.toAmount() })
}
