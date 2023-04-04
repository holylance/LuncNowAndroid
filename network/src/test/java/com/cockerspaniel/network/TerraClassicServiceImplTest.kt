package com.cockerspaniel.network

import com.cockerspaniel.network.internal.TerraClassicApiService
import com.cockerspaniel.network.internal.TerraClassicServiceImpl
import com.cockerspaniel.network.mockdata.TransactionListResponseMock
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
    private lateinit var terraClassicService: TerraClassicServiceImpl

    @BeforeEach
    fun setUp() {
        terraClassicService = TerraClassicServiceImpl(terraClassicApiService)
    }

    @Nested
    inner class Log {
        @Test
        fun `when call getTransactions, reach TerraClassicApiService's getTransactions`() {
            whenever(terraClassicApiService.getTransactions(any(), any()))
                .thenReturn(Single.just(TransactionListResponseMock.generate()))

            terraClassicService.getTransactions()

            verify(terraClassicApiService).getTransactions(any(), any())
        }
    }
}
