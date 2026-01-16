package com.example.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.ValidateLoginUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val validateLoginUseCase: ValidateLoginUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val isLoginEnabled: StateFlow<Boolean> = combine(_email, _password) { email, pass ->
        validateLoginUseCase.execute(email, pass)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun onEmailChanged(text: String) {
        if (!text.any { it in 'а'..'я' || it in 'А'..'Я' }) {
            _email.value = text
        }
    }

    fun onPasswordChanged(text: String) {
        _password.value = text
    }
}