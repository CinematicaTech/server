package com.cinematica.server.app.dependencies.configuration

data class DatabaseConfig(
    val url: String,
    val user: String,
    val password: String?,
)