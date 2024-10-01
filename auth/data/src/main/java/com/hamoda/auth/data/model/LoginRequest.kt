package com.hamoda.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
class LoginRequest(
    val email: String,
    val password: String
)