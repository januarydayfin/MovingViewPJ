package com.krayapp.movingviewpj

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CarApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@CarApp)
            modules(Koin.getModule())
        }
    }

}