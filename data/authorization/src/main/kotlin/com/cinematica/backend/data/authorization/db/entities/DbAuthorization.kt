package com.cinematica.backend.data.authorization.db.entities

data class DbAuthorization(
    val email: String,
    val password: String,
    val salt: String
)