package com.cinematica.backend.foundation.hashing

interface HashingRepository {
    fun hashPassword(password: String): String

    fun validPassword(password: String, hashedPassword: String): Boolean
}