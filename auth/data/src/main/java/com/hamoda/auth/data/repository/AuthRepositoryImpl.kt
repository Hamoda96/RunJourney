package com.hamoda.auth.data.repository

import com.hamoda.auth.data.model.RegisterRequest
import com.hamoda.auth.domain.repository.AuthRepository
import com.hamoda.core.data.networking.post
import com.hamoda.core.domain.util.DataError
import com.hamoda.core.domain.util.EmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(private val httpClient: HttpClient) : AuthRepository {

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