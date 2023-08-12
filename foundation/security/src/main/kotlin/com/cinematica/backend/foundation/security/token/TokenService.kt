package com.main.security.token

import com.cinematica.backend.foundation.security.token.data.TokenClaim
import com.cinematica.backend.foundation.security.token.data.TokenConfig

interface TokenService {
    fun generate(
        config: TokenConfig,
        vararg claims: TokenClaim
    ): String
}