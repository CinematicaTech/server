package com.cinematica.backend.domain.authorization.old.types.common

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationResponse(
    val token: String
)