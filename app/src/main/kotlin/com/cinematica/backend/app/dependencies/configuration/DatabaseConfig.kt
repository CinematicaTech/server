package com.cinematica.backend.app.dependencies.configuration

data class DatabaseConfig(
    val url: String,
    val user: String,
    val password: String?,
)