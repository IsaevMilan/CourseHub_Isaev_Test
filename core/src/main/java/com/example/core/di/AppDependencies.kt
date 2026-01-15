package com.example.core.di




import com.example.domain.repository.CourseRepository
import retrofit2.Retrofit

// Этот интерфейс говорит: "Любая фича может попросить у меня это"
interface AppDependencies {
    fun retrofit(): Retrofit
    fun courseRepository(): CourseRepository
}