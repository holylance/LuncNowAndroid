package com.cockerspaniel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Amount(
    val denom: String,
    val amount: String,
) : Parcelable
