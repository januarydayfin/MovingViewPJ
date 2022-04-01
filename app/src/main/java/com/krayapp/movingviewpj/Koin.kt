package com.krayapp.movingviewpj

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Koin {
    fun getModule() = module {
        viewModel{MainViewModel()}
    }
}