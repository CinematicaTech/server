package com.cinematica.backend.foundation.security.hashing.data

data class SaltedHash(
    val hash: String,
    val salt: String
)
