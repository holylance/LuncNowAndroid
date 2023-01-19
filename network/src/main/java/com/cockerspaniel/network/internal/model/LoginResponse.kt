package com.cockerspaniel.network.internal.model

import android.os.Parcelable
import com.cockerspaniel.network.model.LoginToken
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    val token: String
) : Parcelable {
    fun toLoginToken() = LoginToken(token)
}
