package com.example.domain.repository

import com.example.domain.models.Courses
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getCourses(): Flow<List<Courses>>
    suspend fun toggleLike(courseId: Int) // Room
}
