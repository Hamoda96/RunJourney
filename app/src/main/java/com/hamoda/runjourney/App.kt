package com.hamoda.runjourney

import android.app.Application
import com.hamoda.auth.data.di.authDataModule
import com.hamoda.auth.presentation.di.authViewModelModule
import com.hamoda.core.data.di.coreDataModule
import com.hamoda.core.database.di.databaseModule
import com.hamoda.run.location.di.locationModule
import com.hamoda.run.network.di.networkModule
import com.hamoda.run.presntation.di.runViewModelModule
import com.hamoda.runjourney.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                authDataModule,
                authViewModelModule,
                coreDataModule,
                runViewModelModule,
                locationModule,
                databaseModule,
                networkModule
            )
        }
    }
}