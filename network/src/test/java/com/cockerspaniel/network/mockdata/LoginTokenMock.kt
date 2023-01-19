package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.model.LoginToken

object LoginTokenMock {
    fun generate(): LoginToken {
        return LoginToken(token = "token")
    }
}
