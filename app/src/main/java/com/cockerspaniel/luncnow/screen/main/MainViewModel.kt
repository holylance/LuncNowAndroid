package com.cockerspaniel.luncnow.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cockerspaniel.luncnow.screen.base.BaseViewModel
import com.cockerspaniel.luncnow.screen.main.model.BurnLuncItem
import com.cockerspaniel.luncnow.util.formatNoSymbol
import java.math.BigDecimal

class MainViewModel : BaseViewModel() {

    private val _viewState = MutableLiveData<List<BurnLuncItem>>()
    val viewState: LiveData<List<BurnLuncItem>> = _viewState

    init {
        _viewState.postValue(createItems())
    }

    private fun createItems(): List<BurnLuncItem> {
        return listOf(
            BurnLuncItem(
                ranking = 1,
                name = "name1",
                amount = BigDecimal("1000").formatNoSymbol()
            ),
            BurnLuncItem(
                ranking = 2,
                name = "name2",
                amount = BigDecimal("24000").formatNoSymbol()
            ),
            BurnLuncItem(
                ranking = 3,
                name = "name3",
                amount = BigDecimal("703000").formatNoSymbol()
            )
        )
    }
}
