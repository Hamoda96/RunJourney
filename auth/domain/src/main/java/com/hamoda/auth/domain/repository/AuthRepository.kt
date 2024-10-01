package com.hamoda.auth.domain.repository

import com.hamoda.core.domain.util.DataError
import com.hamoda.core.domain.util.EmptyDataResult

interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyDataResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyDataResult<DataError.Network>
}