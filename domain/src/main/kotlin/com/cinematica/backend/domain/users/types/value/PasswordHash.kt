package com.cinematica.backend.domain.users.types.value

import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class PasswordHash private constructor(val hash: String) {
    companion object : SafeConstructor<PasswordHash, String>() {
        override val displayName: String by wrapperTypeName()
        private val SIZE = 5..128

        context(ValidationFailureHandler)
        override fun create(value: String): PasswordHash {
            return when {
                value.isEmpty() -> onFail(FailureMessage.ofBlank())
                value.length !in SIZE -> onFail(FailureMessage.ofSize(SIZE))
                else -> PasswordHash(value)
            }
        }
    }
}