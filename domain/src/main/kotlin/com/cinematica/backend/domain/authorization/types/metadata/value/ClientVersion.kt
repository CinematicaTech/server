package com.cinematica.backend.domain.authorization.types.metadata.value

import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class ClientVersion private constructor(val double: Double) {
    companion object : SafeConstructor<ClientVersion, Double>() {
        override val displayName: String by wrapperTypeName()

        context(ValidationFailureHandler)
        override fun create(value: Double): ClientVersion {
            return when {
                value < 1 -> onFail(FailureMessage.ofMin(1))
                else -> ClientVersion(value)
            }
        }
    }
}