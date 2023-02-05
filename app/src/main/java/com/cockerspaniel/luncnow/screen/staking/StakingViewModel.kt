package com.cockerspaniel.luncnow.screen.staking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cockerspaniel.luncnow.repository.StakingUserRepository
import com.cockerspaniel.luncnow.screen.base.BaseValues.DECIMAL
import com.cockerspaniel.luncnow.screen.base.BaseViewModel
import com.cockerspaniel.luncnow.screen.staking.model.StakingItem
import com.cockerspaniel.luncnow.screen.staking.model.StakingUser
import com.cockerspaniel.luncnow.usecase.StakingUseCase
import com.cockerspaniel.luncnow.util.asLiveData
import com.cockerspaniel.luncnow.util.formatNoSymbol
import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.rx.SchedulerProvider
import com.cockerspaniel.network.model.Staking
import java.math.BigDecimal

class StakingViewModel(
    private val stakingUserRepository: StakingUserRepository,
    private val useCase: StakingUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _viewState = MutableLiveData<List<ListItemModel>>()
    val viewState: LiveData<List<ListItemModel>> = _viewState

    private val _errorEvent = MutableLiveData<Throwable>()
    val errorEvent = _errorEvent.asLiveData()

    fun fetchStaking() {
        val list = stakingUserRepository.getUsers()
        val listAddress = list.users.map { it.address }
        useCase.fetchStaking(listAddress)
            .subscribeOn(schedulerProvider.io())
            .subscribe(::onSuccess, ::onError)
            .let(::bind)
    }

    private fun onSuccess(listStaking: List<Staking>) {
        val list = stakingUserRepository.getUsers()
        val sortedList = mutableListOf<StakingItem>()
        var ranking = 1
        listStaking.map { staking ->
            list.users.map { user ->
                if (staking.address == user.address)
                    sortedList.add(generateItem(user, staking, ranking))
            }
        }
        _viewState.postValue(
            sortedList.sortedByDescending { it.amountNum }.map { it.copy(ranking = ranking++) }
        )
    }

    private fun generateItem(user: StakingUser, staking: Staking, ranking: Int) = StakingItem(
        address = user.address,
        name = user.name,
        ranking = ranking,
        country = user.country,
        countryFull = user.countryFull,
        amountNum = staking.delegationTotal * BigDecimal(DECIMAL),
        amount = (staking.delegationTotal * BigDecimal(DECIMAL)).formatNoSymbol()
    )

    private fun onError(throwable: Throwable) {
        _errorEvent.postValue(throwable)
    }
}
