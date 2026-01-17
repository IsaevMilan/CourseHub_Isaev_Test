package com.example.data.remote.repository


import com.example.data.remote.network_client.CourseApi
import com.example.domain.models.Courses
import com.example.domain.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val api: CourseApi
) : CourseRepository {

    override fun getCourses(): Flow<List<Courses>> = flow {
        val dto = api.getCourses()
        val domainList = dto.courses.map {
            Courses(
                id = it.id,
                title = it.title,
                text = it.text,
                price = it.price,
                rate = it.rate,
                startDate = it.startDate,
                hasLike = it.hasLike,
                publishDate = it.publishDate
            )
        }

        emit(domainList)
    }
        .flowOn(Dispatchers.IO)

    override suspend fun toggleLike(courseId: Int) {
        // Тут будет логика Room или нет))) пока так :):)
    }
}