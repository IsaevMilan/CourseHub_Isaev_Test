package com.example.data.remote.models

import com.google.gson.annotations.SerializedName

data class CoursesContainerDto(
    @SerializedName("courses") val courses: List<CourseDto>
)

data class CourseDto(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: Double,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)
