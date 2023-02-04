package com.cockerspaniel.network

import com.cockerspaniel.network.internal.TerraClassicApiService
import com.google.gson.GsonBuilder
import com.cockerspaniel.network.mockdata.TempRequestMock
import com.cockerspaniel.network.mockdata.TransactionMock
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal class TerraClassicApiServiceTest {
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
    private val service = retrofit.create(TerraClassicApiService::class.java)
    private val transaction = TransactionMock.generate()

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

            service.postLogin(TempRequestMock.generate())
                .test()
                .assertValue {
                    it.token == loginToken.token
                }
                .dispose()
        }

        @Test
        fun `getTodos handles error response`() {
            setFailedResponseInMockWebServer()

            service.postLogin(TempRequestMock.generate())
                .test()
                .assertError { throwable ->
                    throwable.message == "HTTP 401 Client Error"
                }
                .dispose()
        }
    }
}
