package com.cockerspaniel.network

import com.cockerspaniel.network.internal.TerraClassicApiService
import com.cockerspaniel.network.internal.TerraClassicServiceImpl
import com.cockerspaniel.network.mockdata.TempRequestMock
import com.cockerspaniel.network.mockdata.TransactionResponseMock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class TerraClassicServiceImplTest {

    private val terraClassicApiService: TerraClassicApiService = mock()
    private lateinit var transactionService: TerraClassicServiceImpl

    @BeforeEach
    fun setUp() {
        transactionService = TerraClassicServiceImpl(terraClassicApiService)
    }

    @Nested
    inner class Log {
        @Test
        fun `when call postTransaction, reach TransactionApiService's postTransaction`() {
            val transactionRequest = TempRequestMock.generate()
            whenever(TerraClassicApiService.postTransaction(transactionRequest))
                .thenReturn(Single.just(TransactionResponseMock.generate()))

            TerraClassicService.Transaction(transactionRequest.email, transactionRequest.password)

            verify(TerraClassicApiService).postTransaction(any())
        }
    }
}
