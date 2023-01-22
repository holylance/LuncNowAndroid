package com.cockerspaniel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionList(
    val limit: Int,
    val txs: List<TransactionInfo>
) : Parcelable
