package com.hamoda.auth.data.di

import com.hamoda.auth.data.EmailPatternValidator
import com.hamoda.auth.domain.model.AuthFeatureScope
import com.hamoda.auth.domain.model.PatternValidator
import com.hamoda.auth.domain.model.UserDataValidator
import org.koin.dsl.module

val authDataModule = module {
    scope<AuthFeatureScope> {
        scoped<PatternValidator> {
            EmailPatternValidator
        }

        scoped {
            UserDataValidator(patternValidator = get())
        }
    }
}