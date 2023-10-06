package com.cinematica.backend.domain.users.types.value

import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class UserPassword private constructor(val string: String) {
    companion object : SafeConstructor<UserPassword, String>() {
        override val displayName: String by wrapperTypeName()
        private val SIZE = 5..128
        private val passwordPattern = Regex("^[a-zA-Z0-9_]+$")

        context(ValidationFailureHandler)
        override fun create(value: String): UserPassword {
            return when {
                value.isEmpty() -> onFail(FailureMessage.ofBlank())
                value.length !in SIZE -> onFail(FailureMessage.ofSize(SIZE))
                !passwordPattern.matches(value) -> onFail(FailureMessage.ofPattern(passwordPattern))
                else -> UserPassword(value)
            }
        }
    }
}