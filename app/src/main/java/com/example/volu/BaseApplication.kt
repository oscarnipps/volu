package com.example.volu

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}


/*
 * Defines the mapping from a given / actual model (T) to a required model (K) and vice versa
 * i.e from a network model (T which can be a request or response model from the network) to a domain model (T)
 *
 * Created by oscar ekesiobi 10/22/2021
 */