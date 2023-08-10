package com.cinematica.backend.domain.authorization.types.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationStateResponse(
    val state: String
)