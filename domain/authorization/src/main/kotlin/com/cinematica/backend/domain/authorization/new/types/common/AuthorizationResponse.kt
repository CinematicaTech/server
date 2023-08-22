package com.cinematica.backend.domain.authorization.new.types.common

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationResponse(
    val token: String
)