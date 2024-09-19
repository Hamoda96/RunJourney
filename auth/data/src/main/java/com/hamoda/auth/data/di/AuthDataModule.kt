package com.hamoda.auth.data.di

import com.hamoda.auth.data.EmailPatternValidator
import com.hamoda.auth.data.repository.AuthRepositoryImpl
import com.hamoda.auth.domain.model.AuthFeatureScope
import com.hamoda.auth.domain.model.PatternValidator
import com.hamoda.auth.domain.model.UserDataValidator
import com.hamoda.auth.domain.repository.AuthRepository
import org.koin.dsl.module

val authDataModule = module {
    scope<AuthFeatureScope> {
        scoped<PatternValidator> {
            EmailPatternValidator
        }

        scoped {
            UserDataValidator(patternValidator = get())
        }

        scoped<AuthRepository> {
            AuthRepositoryImpl(httpClient = get())
        }
    }
}