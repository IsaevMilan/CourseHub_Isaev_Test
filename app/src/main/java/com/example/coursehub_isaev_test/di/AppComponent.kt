package com.example.coursehub_isaev_test.di

import com.example.core.di.NetworkModule
import com.example.data.remote.di.DataModule
import com.example.domain.repository.CourseRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataModule::class])
interface AppComponent {
    fun courseRepository(): CourseRepository

    // Инъекции для фрагментов тут сделаю
}