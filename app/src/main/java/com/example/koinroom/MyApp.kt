package com.example.koinroom

import android.app.Application
import com.example.koinroom.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApp) //to provide android application context to koin container thus allowing koin to manage the lifecycle of dependencies correctly and ensure resources are correctly released as needed
            modules(databaseModule)
        }
    }
}