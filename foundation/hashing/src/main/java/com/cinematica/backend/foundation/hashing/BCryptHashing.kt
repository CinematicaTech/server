package com.cinematica.backend.foundation.hashing

import org.mindrot.jbcrypt.BCrypt

class BCryptHashing : HashingRepository {
    override fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    override fun validPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }
}