package com.cockerspaniel.network.internal.model

import android.os.Parcelable
import com.cockerspaniel.network.model.Transaction
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionResponse(
    val value: TransactionValueResponse
) : Parcelable {

    fun toTransaction() = Transaction(
        value = value.toTransactionValue()
    )
}
