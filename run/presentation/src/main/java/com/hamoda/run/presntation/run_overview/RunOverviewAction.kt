package com.hamoda.run.presntation.run_overview

import com.hamoda.run.presntation.active_run.model.RunUi

sealed interface RunOverviewAction {
    data object OnStartClick : RunOverviewAction
    data object OnLogoutClick : RunOverviewAction
    data object OnAnalyticsClick : RunOverviewAction
    data class DeleteRun(val runUi: RunUi): RunOverviewAction
}