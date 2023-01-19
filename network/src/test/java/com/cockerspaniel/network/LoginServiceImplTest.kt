package com.cockerspaniel.network

import com.cockerspaniel.network.internal.LoginApiService
import com.cockerspaniel.network.internal.LoginServiceImpl
import com.cockerspaniel.network.mockdata.LoginRequestMock
import com.cockerspaniel.network.mockdata.LoginResponseMock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class LoginServiceImplTest {

    private val loginApiService: LoginApiService = mock()
    private lateinit var loginService: LoginServiceImpl

    @BeforeEach
    fun setUp() {
        loginService = LoginServiceImpl(loginApiService)
    }

    @Nested
    inner class Login {
        @Test
        fun `when call postLogin, reach loginApiService's postLogin`() {
            val loginRequest = LoginRequestMock.generate()
            whenever(loginApiService.postLogin(loginRequest))
                .thenReturn(Single.just(LoginResponseMock.generate()))

            loginService.login(loginRequest.email, loginRequest.password)

            verify(loginApiService).postLogin(any())
        }
    }
}
