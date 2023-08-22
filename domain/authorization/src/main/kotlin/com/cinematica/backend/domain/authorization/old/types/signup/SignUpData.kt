package com.cinematica.backend.domain.authorization.old.types.signup

data class SignUpData(
    val email: String,
    val password: String,
    val salt: String
)