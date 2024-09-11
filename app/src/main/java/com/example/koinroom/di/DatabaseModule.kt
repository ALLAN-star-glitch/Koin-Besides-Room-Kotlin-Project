package com.example.koinroom.di

import com.example.koinroom.repository.DatabaseRepository
import com.example.koinroom.viewmodels.DatabaseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single{provideDatabase(androidContext()) }

    single {providerDao(get())}

    factory { DatabaseRepository(get()) } //factory creates a new instance every time it's requested

    viewModel { DatabaseViewModel(get()) }
}