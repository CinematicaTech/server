package com.cinematica.backend.domain.authorization.types.authorization

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationResponse(
    val token: String
)