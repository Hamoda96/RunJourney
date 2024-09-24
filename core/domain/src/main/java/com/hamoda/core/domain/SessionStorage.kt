package com.hamoda.core.domain

import com.hamoda.core.domain.AuthInfo

interface SessionStorage {

    suspend fun get(): AuthInfo?

    suspend fun set(authInfo: AuthInfo?)
}