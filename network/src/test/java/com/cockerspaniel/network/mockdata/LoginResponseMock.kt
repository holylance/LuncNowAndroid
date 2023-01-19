package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.LoginResponse

object LoginResponseMock {

    fun generate(): LoginResponse {
        return LoginResponse(token = "token")
    }
}
