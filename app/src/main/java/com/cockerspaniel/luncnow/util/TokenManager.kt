package com.cockerspaniel.luncnow.util

import com.cockerspaniel.network.model.LoginToken
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

object TokenManager {

    private val tokenSubject = BehaviorSubject.create<LoginToken>()

    fun getCurrentToken(): Observable<LoginToken> {
        return tokenSubject.hide()
    }

    fun setToken(token: LoginToken) {
        tokenSubject.onNext(token)
    }
}
