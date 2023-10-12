package com.cinematica.backend.foundation.hashing

import org.mindrot.jbcrypt.BCrypt

class BCryptHashing(
    private val salt: Salt
) : HashingRepository {
    override fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, salt.value)
    }

    override fun validPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }
}