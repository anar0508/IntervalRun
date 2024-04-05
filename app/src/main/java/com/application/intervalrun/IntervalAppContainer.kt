package com.application.intervalrun

import android.app.Application
import com.application.intervalrun.data.AppDataContainer
import com.application.intervalrun.data.AppDataWrapper

class IntervalAppContainer : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppDataWrapper

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}