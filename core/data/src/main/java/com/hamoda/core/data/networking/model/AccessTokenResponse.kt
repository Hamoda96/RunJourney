package com.hamoda.core.data.networking.model

import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenResponse(
    val accessToken: String,
    val expirationTimestamp: String
)