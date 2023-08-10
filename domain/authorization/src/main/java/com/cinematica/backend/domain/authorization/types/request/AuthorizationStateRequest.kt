package com.cinematica.backend.domain.authorization.types.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationStateRequest(
    val email: String
)