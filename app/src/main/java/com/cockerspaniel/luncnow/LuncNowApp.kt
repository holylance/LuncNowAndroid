package com.cockerspaniel.luncnow

import android.app.Application
import com.cockerspaniel.luncnow.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class LuncNowApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
    private fun initKoin() {
        startKoin {
            androidContext(this@LuncNowApp)
            modules(koinModules())
        }
    }
    private fun koinModules(): List<Module> {
        return listOf(appModule)
    }
}
