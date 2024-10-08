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
import com.hamoda.auth.domain.repository.AuthRepository
import com.hamoda.auth.presentation.R
import com.hamoda.core.domain.util.DataError
import com.hamoda.core.domain.util.Result
import com.hamoda.core.presentation.ui.UiText
import com.hamoda.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

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
        when (action) {
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }
            else -> Unit
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = authRepository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isRegistering = false)

            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(RegisterEvent.Error(error = UiText.StringResource(R.string.error_user_exists)))
                    } else {

                        eventChannel.send(RegisterEvent.Error(error = result.error.asUiText()))
                    }
                }

                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}