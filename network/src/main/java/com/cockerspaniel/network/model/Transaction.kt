package com.cockerspaniel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.ZonedDateTime

@Parcelize
data class Transaction(
    val tx: TransactionInfo,
    val logs: List<Log>,
    val timestamp: ZonedDateTime
) : Parcelable
