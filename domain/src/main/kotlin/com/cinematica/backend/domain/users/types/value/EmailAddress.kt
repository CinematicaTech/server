package com.cinematica.backend.domain.users.types.value

import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class EmailAddress private constructor(val string: String) {
    companion object : SafeConstructor<EmailAddress, String>() {
        override val displayName: String by wrapperTypeName()
        val SIZE = 3..200
        private val emailPattern = Regex(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        )

        context(ValidationFailureHandler)
        override fun create(value: String): EmailAddress {
            return when {
                value.isEmpty() -> onFail(FailureMessage.ofBlank())
                value.length !in SIZE -> onFail(FailureMessage.ofSize(SIZE))
                !emailPattern.matches(value) -> onFail(FailureMessage.ofPattern(emailPattern))
                else -> EmailAddress(value)
            }
        }
    }
}