package com.example.feature_main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Courses
import com.example.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _courses = MutableStateFlow<List<Courses>>(emptyList())
    val courses = _courses.asStateFlow()

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            getCoursesUseCase()
                .catch {  }
                .collect { _courses.value = it }
        }
    }

    fun sortCourses() {
        // Сортировка по убыванию даты публикации
        _courses.value = _courses.value.sortedByDescending { it.publishDate }
    }


    fun toggleLike(courseId: Int) {
        val currentList = _courses.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == courseId }
        if (index != -1) {
            val course = currentList[index]
            currentList[index] = course.copy(hasLike = !course.hasLike)
            _courses.value = currentList
        }
    }
}