package com.example.youtube_6month

import android.app.Application
import com.example.youtube_6month.di.koinModules
import com.example.youtube_6month.repository.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            modules(koinModules)
        }
    }
}