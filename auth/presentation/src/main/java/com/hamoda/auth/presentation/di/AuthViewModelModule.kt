package com.hamoda.auth.presentation.di

import com.hamoda.auth.domain.model.AuthFeatureScope
import com.hamoda.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.getScopeId
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin


val authViewModelModule = module {
    val scope = getKoin().getOrCreateScope<AuthFeatureScope>(AuthFeatureScope.getScopeId())
    viewModel {
        RegisterViewModel(userDataValidator = scope.get(), authRepository = scope.get())
    }
}