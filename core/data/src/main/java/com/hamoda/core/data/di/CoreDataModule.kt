package com.hamoda.core.data.di

import android.content.Context
import com.hamoda.core.data.auth.EncryptedSessionStorage
import com.hamoda.core.data.networking.HttpClientFactory
import com.hamoda.core.data.run.OfflineFirstRunRepository
import com.hamoda.core.domain.SessionStorage
import com.hamoda.core.domain.run.RunRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(sessionStorage = get()).build()
    }

    single { androidContext().getSharedPreferences("default_prefs", Context.MODE_PRIVATE) }

    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()

    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}