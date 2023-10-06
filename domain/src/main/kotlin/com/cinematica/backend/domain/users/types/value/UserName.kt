package com.cinematica.backend.domain.users.types.value

import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class UserName private constructor(val string: String) {
    companion object : SafeConstructor<UserName, String>() {
        override val displayName: String by wrapperTypeName()
        private val SIZE = 3..35
        private val nicknamePattern = Regex("^[a-zA-Z0-9_]+$")

        context(ValidationFailureHandler)
        override fun create(value: String): UserName {
            return when {
                value.isEmpty() -> onFail(FailureMessage.ofBlank())
                value.length !in SIZE -> onFail(FailureMessage.ofSize(SIZE))
                !nicknamePattern.matches(value) -> onFail(FailureMessage.ofPattern(nicknamePattern))
                else -> UserName(value)
            }
        }
    }
}