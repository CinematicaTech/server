package com.cinematica.backend.domain.authorization.types.metadata.value

import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class ClientIpAddress private constructor(val string: String) {
    companion object : SafeConstructor<ClientIpAddress, String>() {
        override val displayName by wrapperTypeName()

        context(ValidationFailureHandler)
        override fun create(value: String): ClientIpAddress {
            return when (value.length) {
                0 -> onFail(FailureMessage.ofBlank())
                else -> ClientIpAddress(value)
            }
        }
    }
}