package com.cockerspaniel.network.internal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(
    val email: String,
    val password: String
) : Parcelable
