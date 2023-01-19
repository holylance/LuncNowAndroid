package com.cockerspaniel.luncnow.di

import com.google.gson.GsonBuilder
import com.cockerspaniel.network.LoginApiFactory
import com.cockerspaniel.luncnow.screen.main.MainViewModel
import com.cockerspaniel.luncnow.util.TokenManager
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { androidApplication().getKoin() }
    factory { GsonBuilder().create() }

    single { LoginApiFactory.create() }
    single { TokenManager }

    viewModel<MainViewModel>()
}
