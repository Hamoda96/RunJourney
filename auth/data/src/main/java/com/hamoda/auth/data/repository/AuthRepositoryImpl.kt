package com.hamoda.auth.data.repository

import com.hamoda.auth.data.model.LoginRequest
import com.hamoda.auth.data.model.LoginResponse
import com.hamoda.auth.data.model.RegisterRequest
import com.hamoda.auth.domain.repository.AuthRepository
import com.hamoda.core.data.networking.post
import com.hamoda.core.domain.AuthInfo
import com.hamoda.core.domain.SessionStorage
import com.hamoda.core.domain.util.DataError
import com.hamoda.core.domain.util.EmptyDataResult
import com.hamoda.core.domain.util.Result
import com.hamoda.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(email = email, password = password)
        )

        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }

        return result.asEmptyDataResult()
    }

    override suspend fun register(
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password
            )
        )
    }
}