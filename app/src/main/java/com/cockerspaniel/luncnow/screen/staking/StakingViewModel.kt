package com.cockerspaniel.luncnow.screen.staking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cockerspaniel.luncnow.screen.base.BaseViewModel
import com.cockerspaniel.luncnow.util.asLiveData
import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.rx.SchedulerProvider

class StakingViewModel(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _viewState = MutableLiveData<List<ListItemModel>>(emptyList())
    val viewState: LiveData<List<ListItemModel>> = _viewState

    private val _errorEvent = MutableLiveData<Throwable>()
    val errorEvent = _errorEvent.asLiveData()

    fun fetchStaking() {

    }

    private fun onError(throwable: Throwable) {
        _errorEvent.postValue(throwable)
    }
}
