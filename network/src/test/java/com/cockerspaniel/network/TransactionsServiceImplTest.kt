package com.cockerspaniel.network

import com.cockerspaniel.network.internal.TransactionsApiService
import com.cockerspaniel.network.internal.TransactionsServiceImpl
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

internal class TransactionsServiceImplTest {

    private val transactionsApiService: TransactionsApiService = mock()
    private lateinit var transactionService: TransactionsServiceImpl

    @BeforeEach
    fun setUp() {
        transactionService = TransactionsServiceImpl(transactionsApiService)
    }

    @Nested
    inner class Log {
        @Test
        fun `when call postTransaction, reach TransactionApiService's postTransaction`() {
            val transactionRequest = TempRequestMock.generate()
            whenever(TransactionsApiService.postTransaction(transactionRequest))
                .thenReturn(Single.just(TransactionResponseMock.generate()))

            TransactionsService.Transaction(transactionRequest.email, transactionRequest.password)

            verify(TransactionsApiService).postTransaction(any())
        }
    }
}
