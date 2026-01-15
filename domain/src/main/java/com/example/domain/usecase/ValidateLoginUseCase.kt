package com.example.domain.usecase

import javax.inject.Inject

class ValidateLoginUseCase @Inject constructor() {

    private val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")

    fun execute(email: String, pass: String): Boolean {
        return emailRegex.matches(email) && pass.isNotBlank()
    }
}