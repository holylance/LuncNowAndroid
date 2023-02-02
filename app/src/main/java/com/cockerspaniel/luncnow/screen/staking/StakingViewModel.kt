package com.cockerspaniel.luncnow.screen.staking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cockerspaniel.luncnow.repository.StakingUserRepository
import com.cockerspaniel.luncnow.screen.base.BaseViewModel
import com.cockerspaniel.luncnow.screen.staking.model.StakingItem
import com.cockerspaniel.luncnow.util.asLiveData
import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.rx.SchedulerProvider
import java.math.BigDecimal

class StakingViewModel(
    private val stakingUserRepository: StakingUserRepository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _viewState = MutableLiveData<List<ListItemModel>>(emptyList())
    val viewState: LiveData<List<ListItemModel>> = _viewState

    private val _errorEvent = MutableLiveData<Throwable>()
    val errorEvent = _errorEvent.asLiveData()

    fun fetchStaking() {
        val list = stakingUserRepository.getUsers()
        _viewState.postValue(
            list.users.map {
                StakingItem(
                    address = it.address,
                    name = it.name,
                    ranking = 1,
                    country = it.country,
                    countryFull = it.countryFull,
                    amountNum = BigDecimal.ONE,
                    amount = "1"
                )
            }
        )
    }

    private fun onError(throwable: Throwable) {
        _errorEvent.postValue(throwable)
    }
}
