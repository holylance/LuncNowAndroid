package com.cockerspaniel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attribute(
    val key: String,
    val value: String,
) : Parcelable
