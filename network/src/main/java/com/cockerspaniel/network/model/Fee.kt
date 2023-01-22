package com.cockerspaniel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fee(
    val amount: List<Amount>
) : Parcelable
