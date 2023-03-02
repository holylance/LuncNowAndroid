package com.cockerspaniel.luncnow.screen.base

import androidx.lifecycle.ViewModel
import com.cockerspaniel.luncnow.screen.base.BaseValues.NO_NAME
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    var isInitialLoad = true
        private set

    fun fragmentResumed() {
        isInitialLoad = false
    }

    protected open fun bind(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun getMemo(memo: String): String {
        return memo.ifBlank { NO_NAME }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
