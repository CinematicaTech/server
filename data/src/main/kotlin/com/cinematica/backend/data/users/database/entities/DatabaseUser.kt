package com.cinematica.backend.data.users.database.entities

data class DatabaseUser(
    val id: Long,
    val email: String,
    val name: String,
    val password: String
)