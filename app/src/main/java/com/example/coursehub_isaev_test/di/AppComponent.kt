package com.example.coursehub_isaev_test.di

import com.example.core.di.AppDependencies
import com.example.core.di.NetworkModule
import com.example.data.remote.di.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataModule::class])
interface AppComponent : AppDependencies {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}