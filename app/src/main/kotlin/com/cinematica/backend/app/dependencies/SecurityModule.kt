package com.cinematica.backend.app.dependencies

import com.cinematica.backend.foundation.security.hashing.HashingService
import com.cinematica.backend.foundation.security.hashing.SHA256HashingService
import com.cinematica.backend.foundation.security.token.JwtTokenService
import com.cinematica.backend.foundation.security.token.data.TokenConfig
import com.main.security.token.TokenService
import org.koin.dsl.module

val SecurityModule = module {
    single<HashingService> { SHA256HashingService() }
    single<TokenService> { JwtTokenService() }
}