package com.cockerspaniel.luncnow.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cockerspaniel.luncnow.screen.base.BaseViewModel
import com.cockerspaniel.luncnow.screen.main.model.BurnLuncItem
import com.cockerspaniel.luncnow.util.ViewState
import com.cockerspaniel.luncnow.util.asViewState

class MainViewModel : BaseViewModel() {

    private val _viewState = MutableLiveData<ViewState<List<BurnLuncItem>>>()
    val viewState: LiveData<ViewState<List<BurnLuncItem>>> = _viewState

    init {
        _viewState.postValue(createItems().asViewState())
    }

    private fun createItems(): List<BurnLuncItem> {
        return listOf(
            BurnLuncItem(
                id = 1,
                name = "name1",
                description = "description1",
                price = "10",
                icon = ""
            ),
            BurnLuncItem(
                id = 2,
                name = "name2",
                description = "description2",
                price = "100",
                icon = ""
            )
        )
    }
}
