package com.cinematica.backend.domain.authorization.types

import com.cinematica.backend.foundation.authorization.Scope

sealed class AuthorizationsScope : Scope {
    open class Read : AuthorizationsScope() {
        companion object : Read()
    }

    data object Write : Read()
}