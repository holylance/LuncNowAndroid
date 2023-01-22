package com.cockerspaniel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionValue(
    val fee: Fee,
    val memo: String
) : Parcelable
