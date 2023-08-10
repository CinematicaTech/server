package com.cinematica.backend.domain.authorization.types.state

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationStateResponse(
    val state: String
)