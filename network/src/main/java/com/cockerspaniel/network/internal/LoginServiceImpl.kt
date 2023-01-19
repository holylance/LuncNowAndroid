package com.cockerspaniel.network.internal

import com.cockerspaniel.network.LoginService
import com.cockerspaniel.network.internal.model.LoginRequest
import com.cockerspaniel.network.model.LoginToken
import io.reactivex.rxjava3.core.Single

internal class LoginServiceImpl(
    private val loginApiService: LoginApiService
) : LoginService {

    override fun login(
        email: String,
        password: String
    ): Single<LoginToken> {
        return loginApiService.postLogin(LoginRequest(email, password))
            .map { it.toLoginToken() }
    }
}
