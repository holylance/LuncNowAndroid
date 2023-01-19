package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.LoginRequest

object LoginRequestMock {

    fun generate(): LoginRequest {
        return LoginRequest(
            email = "email",
            password = "password"
        )
    }
}
