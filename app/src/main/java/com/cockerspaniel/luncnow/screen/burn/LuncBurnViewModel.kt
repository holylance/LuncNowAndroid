package com.cockerspaniel.luncnow.screen.burn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cockerspaniel.luncnow.screen.base.BaseViewModel
import com.cockerspaniel.luncnow.screen.burn.model.LuncBurnItem
import com.cockerspaniel.luncnow.screen.burn.model.LuncDescriptionItem
import com.cockerspaniel.luncnow.usecase.TransactionsUseCase
import com.cockerspaniel.luncnow.util.asLiveData
import com.cockerspaniel.luncnow.util.formatNoSymbol
import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.rx.SchedulerProvider
import com.cockerspaniel.network.model.TransactionList
import java.math.BigDecimal

class LuncBurnViewModel(
    private val useCase: TransactionsUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _viewState = MutableLiveData<List<ListItemModel>>(emptyList())
    val viewState: LiveData<List<ListItemModel>> = _viewState

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
            var foundAmount = EMPTY_STRING
            info.logs.map { log ->
                val first: String? = log.events.firstOrNull {
                    event -> event.type == RECEIVED &&
                        event.attributes.last().value.contains(LUNC)
                }?.attributes?.last()?.value?.replace(LUNC, EMPTY_STRING)
                first?.let { foundAmount = (first.toBigDecimal() * BigDecimal(DECIMAL)).toString() }
            }
            LuncBurnItem(
                name = getMemo(info.tx.value.memo),
                amountNum = if (foundAmount.isNotBlank()) {
                    BigDecimal(foundAmount)
                } else {
                    BigDecimal.ZERO
                },
                amount = if (foundAmount.isNotBlank()) {
                    BigDecimal(foundAmount).formatNoSymbol()
                } else {
                    EMPTY_STRING
                }
            )
        }

        var ranking = 1
        _viewState.postValue(
            listOf(LuncDescriptionItem()) +
                listItems.sortedByDescending { it.amountNum }.map { it.copy(ranking = ranking++) }
        )
    }

    private fun onError(throwable: Throwable) {
        _errorEvent.postValue(throwable)
    }

    companion object {
        private const val RECEIVED = "coin_received"
        private const val LUNC = "uluna"
        private const val DECIMAL = "0.000001"
        private const val EMPTY_STRING = ""
    }
}
