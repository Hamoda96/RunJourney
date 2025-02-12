package com.hamoda.run.presntation.run_overview

import com.hamoda.run.presntation.active_run.model.RunUi

data class RunOverviewState(
    val runs: List<RunUi> = emptyList()
)
