package com.hamoda.run.data.di

import com.hamoda.core.domain.run.SyncRunScheduler
import com.hamoda.run.data.workers.CreateRunWorker
import com.hamoda.run.data.workers.DeleteRunWorker
import com.hamoda.run.data.workers.FetchRunsWorker
import com.hamoda.run.data.workers.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}