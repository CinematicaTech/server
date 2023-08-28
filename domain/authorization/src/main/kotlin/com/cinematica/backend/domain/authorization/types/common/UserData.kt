package com.cinematica.backend.domain.authorization.types.common

data class UserData(
    val email: String,
    val password: String,
    val salt: String
)