package com.cockerspaniel.luncnow.di

import com.cockerspaniel.luncnow.repository.TransactionsRepository
import com.google.gson.GsonBuilder
import com.cockerspaniel.luncnow.screen.burn.LuncBurnViewModel
import com.cockerspaniel.luncnow.usecase.LuncBurnItemUseCase
import com.cockerspaniel.luncnow.usecase.TransactionsUseCase
import com.cockerspaniel.luncnow.util.rx.DefaultSchedulerProvider
import com.cockerspaniel.luncnow.util.rx.SchedulerProvider
import com.cockerspaniel.luncnow.util.storage.EncryptedPreferencesBuilder
import com.cockerspaniel.luncnow.util.storage.PreferenceStoreImpl
import com.cockerspaniel.network.TerraClassicApiFactory
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.annotation.KoinReflectAPI
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

@KoinReflectAPI
val appModule = module {
    factory { androidApplication().getKoin() }
    factory { GsonBuilder().create() }
    factoryOf(::DefaultSchedulerProvider) bind SchedulerProvider::class
    factory {
        PreferenceStoreImpl(
            EncryptedPreferencesBuilder(
                androidContext()
            ).instantiateEncryptedSharedPreferences(),
            get()
        )
    }

    // Transaction page
    factoryOf(::TransactionsRepository)
    factoryOf(::TransactionsUseCase)
    factoryOf(::LuncBurnItemUseCase)

    single { TerraClassicApiFactory.create() }

    viewModelOf(::LuncBurnViewModel)
}
