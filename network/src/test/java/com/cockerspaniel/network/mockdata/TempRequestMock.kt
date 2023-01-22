package com.cockerspaniel.network.mockdata

object TempRequestMock {

    fun generate(): TempRequest {
        return TempRequest(
            email = "email",
            password = "password"
        )
    }
}
