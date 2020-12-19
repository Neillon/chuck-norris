package com.example.chuck_norris

import android.app.Application
import timber.log.Timber

class ChuckNorrisApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}