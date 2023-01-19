package com.cockerspaniel.network

import com.google.gson.GsonBuilder
import com.cockerspaniel.network.internal.LoginApiService
import com.cockerspaniel.network.mockdata.LoginRequestMock
import com.cockerspaniel.network.mockdata.LoginTokenMock
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal class LoginApiServiceTest {
    private val mockWebServer = MockWebServer()
    private val httpClient = OkHttpClient.Builder()
    private val gson = GsonBuilder().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("").toString())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
        .addConverterFactory(
            GsonConverterFactory
                .create(gson)
        )
        .client(httpClient.build())
        .build()
    private val service = retrofit.create(LoginApiService::class.java)
    private val loginToken = LoginTokenMock.generate()

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    private fun setSuccessResponseInMockWebServer(body: Any) {
        val toJson = gson.toJson(body)

        val response = MockResponse()
            .setResponseCode(200)
            .setBody(toJson)

        mockWebServer.enqueueSilently(response)
    }

    private fun setFailedResponseInMockWebServer() {
        val response = MockResponse()
            .setResponseCode(401)

        mockWebServer.enqueueSilently(response)
    }

    @Nested
    inner class Login {

        @Test
        fun `postLogin handles successful response`() {
            setSuccessResponseInMockWebServer(loginToken)

            service.postLogin(LoginRequestMock.generate())
                .test()
                .assertValue {
                    it.token == loginToken.token
                }
                .dispose()
        }

        @Test
        fun `getTodos handles error response`() {
            setFailedResponseInMockWebServer()

            service.postLogin(LoginRequestMock.generate())
                .test()
                .assertError { throwable ->
                    throwable.message == "HTTP 401 Client Error"
                }
                .dispose()
        }
    }
}
