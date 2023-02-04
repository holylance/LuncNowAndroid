package com.cockerspaniel.luncnow.screen.staking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cockerspaniel.luncnow.repository.StakingUserRepository
import com.cockerspaniel.luncnow.screen.base.BaseValues.DECIMAL
import com.cockerspaniel.luncnow.screen.base.BaseViewModel
import com.cockerspaniel.luncnow.screen.staking.model.StakingItem
import com.cockerspaniel.luncnow.screen.staking.model.StakingUser
import com.cockerspaniel.luncnow.screen.staking.model.StakingUsers
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

    private val _viewState = MutableLiveData<List<ListItemModel>>(emptyList())
    val viewState: LiveData<List<ListItemModel>> = _viewState

    private val _errorEvent = MutableLiveData<Throwable>()
    val errorEvent = _errorEvent.asLiveData()

    fun fetchStaking() {
        val list = stakingUserRepository.getUsers()
        list.users.map { user ->
            useCase.fetchStaking(user.address)
                .subscribeOn(schedulerProvider.io())
                .subscribe({ onSuccess(user, it) }, ::onError)
                .let(::bind)
        }
    }

    private fun onSuccess(user: StakingUser, staking: Staking) {
        var ranking = 1
        val results = _viewState.value
        results?.let { items ->
            val temp = items.toMutableList()
            temp.add(generateItem(user, staking, ranking))
            _viewState.postValue(
                temp.filterIsInstance<StakingItem>().sortedByDescending { it.amountNum }
                    .map { it.copy(ranking = ranking++) }
            )
        } ?: run { _viewState.postValue(listOf(generateItem(user, staking, ranking))) }
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
