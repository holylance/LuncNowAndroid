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

class MainViewModel(
    private val useCase: TransactionsUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _viewState = MutableLiveData<List<BurnLuncItem>>()
    val viewState: LiveData<List<BurnLuncItem>> = _viewState

    private val _errorEvent = MutableLiveData<Throwable>()
    val errorEvent = _errorEvent.asLiveData()

    init {
        fetchTransactions()
    }

    private fun fetchTransactions() {
        useCase.fetchTransactions()
            .subscribeOn(schedulerProvider.io())
            .subscribe(::onSuccess, ::onError)
            .let(::bind)
    }

    private fun onSuccess(list: TransactionList) {
        var ranking = 0
        val listItems = list.txs.map {
            val foundToken = it.tx.value.fee.amount.filter { amount ->
                amount.denom == "uluna"
            }
            BurnLuncItem(
                ranking = ranking++,
                name = it.tx.value.memo,
                amount = BigDecimal(foundToken.first().amount).formatNoSymbol()
            )
        }
        _viewState.postValue(listItems)
    }

    private fun onError(throwable: Throwable) {
        _errorEvent.postValue(throwable)
    }
}
