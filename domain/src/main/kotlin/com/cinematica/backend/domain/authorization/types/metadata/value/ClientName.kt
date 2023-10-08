package com.cinematica.backend.domain.authorization.types.metadata.value

import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class ClientName private constructor(val string: String) {
    companion object : SafeConstructor<ClientName, String>() {
        override val displayName: String by wrapperTypeName()

        context(ValidationFailureHandler)
        override fun create(value: String): ClientName {
            return when (value.length) {
                0 -> onFail(FailureMessage.ofBlank())
                else -> ClientName(value)
            }
        }
    }
}