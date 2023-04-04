package com.cockerspaniel.network

import com.cockerspaniel.network.internal.TerraClassicApiService
import com.google.gson.GsonBuilder
import com.cockerspaniel.network.mockdata.EventResponseMock
import com.cockerspaniel.network.mockdata.AttributeResponseMock
import com.cockerspaniel.network.mockdata.TransactionListResponseMock
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
    private val transactionList = TransactionListResponseMock.generate()

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
    inner class Transactions {

        @Test
        fun `getTransactions handles successful response`() {
            setSuccessResponseInMockWebServer(transactionList)

            service.getTransactions("10", 10)
                .test()
                .assertValue { it == transactionList }
                .dispose()
        }

        @Test
        fun `getTransactions handles error response`() {
            setFailedResponseInMockWebServer()

            service.getTransactions("10", 10)
                .test()
                .assertError { throwable ->
                    throwable.message == "HTTP 401 Client Error"
                }
                .dispose()
        }
    }
}
