package com.example.chuck_norris

import android.app.Application
import com.example.chuck_norris.database.di.DatabaseModule
import com.example.chuck_norris.network.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ChuckNorrisApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        startDependencyGraph()
    }

    /**
     * Setup the initial dependency graph
     */
    private fun startDependencyGraph() {
        startKoin {
            androidContext(this@ChuckNorrisApplication)
            modules(
                listOf(
                    NetworkModule.dependencies,
                    DatabaseModule.dependencies
                )
            )
        }
    }
}