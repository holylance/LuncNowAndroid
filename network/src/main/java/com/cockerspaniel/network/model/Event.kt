package com.cockerspaniel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val type: String,
    val attributes: List<Attribute>
) : Parcelable
