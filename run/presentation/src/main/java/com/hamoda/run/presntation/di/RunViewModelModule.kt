package com.hamoda.run.presntation.di

import com.hamoda.run.domain.RunningTracker
import com.hamoda.run.presntation.active_run.ActiveRunViewModel
import com.hamoda.run.presntation.run_overview.RunOverviewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val runViewModelModule = module {
    singleOf(::RunningTracker)

    viewModel {
        RunOverviewModel()
    }

    viewModel {
        ActiveRunViewModel(get())
    }
}