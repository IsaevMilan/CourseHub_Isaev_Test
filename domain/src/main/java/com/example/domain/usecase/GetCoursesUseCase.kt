package com.example.domain.usecase

import com.example.domain.models.Courses
import com.example.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoursesUseCase {
    class GetCoursesUseCase @Inject constructor(
        private val repository: CourseRepository
    ) {
        operator fun invoke(): Flow<List<Courses>> = repository.getCourses()
    }
}