package com.cinematica.backend.domain.authorization.types.state

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationStateRequest(
    val email: String
)