package com.cockerspaniel.network

import com.cockerspaniel.network.base.internal.RetrofitService
import com.cockerspaniel.network.internal.LoginApiService
import com.cockerspaniel.network.internal.LoginServiceImpl

object LoginApiFactory {
    fun create(): LoginService {
        return LoginServiceImpl(LoginApiService.create(RetrofitService().retrofit))
    }
}
