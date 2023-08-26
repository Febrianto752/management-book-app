package com.example.managebookapp

import android.app.Application
import com.example.managebookapp.data.AppContainer
import com.example.managebookapp.data.AppDataContainer

class ManagementBookApplication: Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
