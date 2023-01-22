package com.cockerspaniel.network.internal.model

import android.os.Parcelable
import com.cockerspaniel.network.model.TransactionInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionInfoResponse(
    val tx: TransactionResponse
) : Parcelable {

    fun toTransactionInfo() = TransactionInfo(
        tx = tx.toTransaction()
    )
}
