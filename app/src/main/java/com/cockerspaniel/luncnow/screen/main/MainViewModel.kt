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
        val listItems = list.txs.map { info ->
            var foundAmount = ""
            info.logs.map { log ->
                val first = log.events.first { event -> event.type == RECEIVED }
                    .attributes.last().value.replace(LUNC, "")
                foundAmount = (first.toBigDecimal() * BigDecimal(DECIMAL)).toString()
            }
            BurnLuncItem(
                name = getMemo(info.tx.value.memo),
                amountNum = BigDecimal(foundAmount),
                amount = BigDecimal(foundAmount).formatNoSymbol()
            )
        }

        var ranking = 1
        _viewState.postValue(
            listItems.sortedByDescending { it.amountNum }
                .map { it.copy(ranking = ranking++) }
        )
    }

    private fun getMemo(memo: String): String {
        return memo.ifBlank { NO_NAME }
    }

    private fun onError(throwable: Throwable) {
        _errorEvent.postValue(throwable)
    }

    companion object {
        private const val RECEIVED = "coin_received"
        private const val LUNC = "uluna"
        private const val DECIMAL = "0.000001"
        private const val NO_NAME = "No Name"
    }
}
