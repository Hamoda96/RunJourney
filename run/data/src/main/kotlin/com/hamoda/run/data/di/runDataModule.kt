package com.hamoda.run.data.di

import com.hamoda.run.data.workers.CreateRunWorker
import com.hamoda.run.data.workers.DeleteRunWorker
import com.hamoda.run.data.workers.FetchRunsWorker
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)
}
