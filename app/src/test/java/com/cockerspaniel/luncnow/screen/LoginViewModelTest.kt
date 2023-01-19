package com.cockerspaniel.luncnow.screen

import com.cockerspaniel.luncnow.InstantExecutorExtension
import com.cockerspaniel.network.LoginService
import com.cockerspaniel.luncnow.screen.login.LoginViewModel
import com.cockerspaniel.luncnow.util.TokenManager
import com.cockerspaniel.network.internal.model.LoginRequest
import com.cockerspaniel.network.model.LoginToken
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class LoginViewModelTest {

    private val apiService: LoginService = mock()
    private val tokenManager: TokenManager = mock()
    private val loginRequest = LoginRequest(email = "email", password = "password")
    private val loginToken = LoginToken(token = "token")

    @BeforeEach
    fun setUp() {
        whenever(apiService.login(any(), any()))
            .thenReturn(Single.just(loginToken))
    }

    @Test
    fun `viewState should emit posted login token`() {
        val viewModel = LoginViewModel(apiService, tokenManager)
        viewModel.login(loginRequest.email, loginRequest.password)

        verify(apiService).login(any(), any())
        verify(tokenManager).setToken(any())
    }
}
