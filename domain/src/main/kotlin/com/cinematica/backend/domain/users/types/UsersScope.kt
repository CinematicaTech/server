package com.cinematica.backend.domain.users.types

import com.cinematica.backend.foundation.authorization.Scope

sealed class UsersScope : Scope {
    open class Read : UsersScope() {
        companion object : Read()
    }

    data object Write : Read()
}