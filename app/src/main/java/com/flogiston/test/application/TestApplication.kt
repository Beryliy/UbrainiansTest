package com.flogiston.test.application

import android.app.Application
import com.flogiston.test.di.appModule
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}