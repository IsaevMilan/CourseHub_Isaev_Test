package com.example.feature_main.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Courses
import com.example.domain.usecase.GetCoursesUseCase
import com.example.feature_main.di.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
class MainViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {


    private val _courses = MutableStateFlow<List<Courses>>(emptyList())
    val courses: StateFlow<List<Courses>> = _courses.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val likedIds = mutableSetOf<Int>()

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(300)
            val newList = getCoursesUseCase().first() // берем один раз
            _courses.value = newList.map { course ->
                course.copy(hasLike = likedIds.contains(course.id))
            }
            _isLoading.value = false
            Log.d("MainViewModel", "Loaded courses: ${_courses.value.map { it.id to it.hasLike }}")
        }
    }

    fun sortCourses() {
        _courses.value = _courses.value.sortedByDescending { it.publishDate }
    }

    fun toggleLike(courseId: Int) {
        if (likedIds.contains(courseId)) likedIds.remove(courseId)
        else likedIds.add(courseId)

        _courses.value = _courses.value.map { course ->
            if (course.id == courseId) course.copy(hasLike = likedIds.contains(courseId))
            else course
        }
    }


    fun fakeLoading() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(600)
            _isLoading.value = false
        }
    }
}
