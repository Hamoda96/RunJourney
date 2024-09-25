package com.hamoda.core.data.di

import com.hamoda.core.data.auth.EncryptedSessionStorage
import com.hamoda.core.data.networking.HttpClientFactory
import com.hamoda.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(sessionStorage = get()).build()
    }

    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}