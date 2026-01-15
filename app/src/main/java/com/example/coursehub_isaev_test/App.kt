package com.example.coursehub_isaev_test

import android.app.Application
import com.example.core.di.AppDependencies
import com.example.core.di.AppDependenciesProvider
import com.example.coursehub_isaev_test.di.DaggerAppComponent
import com.example.coursehub_isaev_test.di.AppComponent

class App : Application(), AppDependenciesProvider {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

    override fun getDependencies(): AppDependencies = appComponent
}