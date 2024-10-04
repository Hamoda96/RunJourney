package com.hamoda.run.presntation.di

import com.hamoda.run.presntation.active_run.ActiveRunViewModel
import com.hamoda.run.presntation.run_overview.RunOverviewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val runViewModelModule = module {
    viewModel {
        RunOverviewModel()
    }

    viewModel {
        ActiveRunViewModel()
    }
}