package com.cockerspaniel.network.internal.model

import android.os.Parcelable
import com.cockerspaniel.network.model.TransactionList
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionListResponse(
    val limit: Int,
    val txs: List<TransactionInfoResponse>
) : Parcelable {

    fun toTransactionList() = TransactionList(
        limit = limit,
        txs = txs.map { it.toTransactionInfo() }
    )
}
