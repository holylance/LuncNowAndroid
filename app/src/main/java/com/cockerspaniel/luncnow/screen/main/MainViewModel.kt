package com.cockerspaniel.luncnow.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cockerspaniel.luncnow.screen.base.BaseViewModel
import com.cockerspaniel.luncnow.screen.main.model.BurnLuncItem
import com.cockerspaniel.luncnow.usecase.TransactionsUseCase
import com.cockerspaniel.luncnow.util.asLiveData
import com.cockerspaniel.luncnow.util.formatNoSymbol
import com.cockerspaniel.luncnow.util.rx.SchedulerProvider
import com.cockerspaniel.network.model.TransactionList
import java.math.BigDecimal
import java.math.BigInteger

class MainViewModel(
    private val useCase: TransactionsUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _viewState = MutableLiveData<List<BurnLuncItem>>(emptyList())
    val viewState: LiveData<List<BurnLuncItem>> = _viewState

    private val _errorEvent = MutableLiveData<Throwable>()
    val errorEvent = _errorEvent.asLiveData()

    fun fetchTransactions() {
        useCase.fetchTransactions()
            .subscribeOn(schedulerProvider.io())
            .subscribe(::onSuccess, ::onError)
            .let(::bind)
    }

    private fun onSuccess(list: TransactionList) {
        var ranking = 1
        val listItems = list.txs.map {
            val foundToken = it.tx.value.fee.amount.filter { amount ->
                amount.denom == LUNC
            }.map { amount ->
                amount.copy(
                    amount = (amount.amount.toBigDecimal() * BigDecimal("0.000001")).toString()
                )
            }
            BurnLuncItem(
                ranking = ranking++,
                name = it.tx.value.memo,
                amount = BigDecimal(foundToken.first().amount).formatNoSymbol()
            )
        }
        _viewState.postValue(listItems)
        //_viewState.postValue(listItems.sortedBy { it.amount })
    }

    private fun onError(throwable: Throwable) {
        _errorEvent.postValue(throwable)
    }

    companion object {
        private const val LUNC = "uluna"
    }
}
