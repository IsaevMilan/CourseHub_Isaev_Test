package com.example.data.remote.network_client

import com.example.data.remote.models.CoursesContainerDto
import retrofit2.http.GET

interface CourseApi {

    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): CoursesContainerDto
}