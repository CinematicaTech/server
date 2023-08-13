package com.cinematica.backend.foundation.security.hashing

import com.cinematica.backend.foundation.security.hashing.data.SaltedHash

interface HashingService {
    fun generateSaltedHash(value: String, saltLength: Int = 32): SaltedHash
    fun verify(value: String, saltedHash: SaltedHash): Boolean
}