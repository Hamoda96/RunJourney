@file:OptIn(ExperimentalFoundationApi::class)

package com.hamoda.auth.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamoda.auth.domain.model.UserDataValidator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RegisterViewModel(
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    init {
        state.email.textAsFlow()
            .onEach { email ->
                val isEmailValid = userDataValidator.isValidEmail(email = email.toString())
                state =
                    state.copy(
                        isEmailValid = isEmailValid,
                        canRegister = isEmailValid && state.passwordValidationState.isValidPassword && !state.isRegistering
                    )
            }.launchIn(viewModelScope)

        state.password.textAsFlow()
            .onEach { password ->
                val isPasswordValid =
                    userDataValidator.validatePassword(password = password.toString())
                state =
                    state.copy(
                        passwordValidationState = isPasswordValid,
                        canRegister = state.isEmailValid && isPasswordValid.isValidPassword && !state.isRegistering
                    )
            }.launchIn(viewModelScope)
    }

    fun onAction(action: RegisterAction) {

    }
}