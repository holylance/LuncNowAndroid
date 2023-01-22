package com.cockerspaniel.network.internal.model

import android.os.Parcelable
import com.cockerspaniel.network.model.TransactionValue
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionValueResponse(
    val fee: FeeResponse,
    val memo: String
) : Parcelable {

    fun toTransactionValue() = TransactionValue(
        fee = fee.toFee(),
        memo = memo
    )
}
