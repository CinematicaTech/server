package com.cinematica.backend.domain.authorization.types

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationResponse(
    val token: String
)