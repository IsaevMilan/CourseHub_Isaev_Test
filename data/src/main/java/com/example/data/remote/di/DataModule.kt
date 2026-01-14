package com.example.data.remote.di

import com.example.data.remote.network_client.CourseApi
import com.example.data.remote.repository.CourseRepositoryImpl
import com.example.domain.repository.CourseRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideCourseApi(retrofit: Retrofit): CourseApi =
        retrofit.create(CourseApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: CourseApi): CourseRepository =
        CourseRepositoryImpl(api)
}