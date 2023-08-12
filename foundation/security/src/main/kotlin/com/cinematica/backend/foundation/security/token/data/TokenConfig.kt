package com.cinematica.backend.foundation.security.token.data

data class TokenConfig(
    val issuer: String,
    val audience: String,
    val expiresIn: Long,
    val secret: String
)
