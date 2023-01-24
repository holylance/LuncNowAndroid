package com.cockerspaniel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaction(
    val tx: TransactionInfo,
    val logs: List<Log>
) : Parcelable
