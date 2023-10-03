package com.cinematica.backend.domain.authorization.types.value

import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class AccessHash private constructor(val string: String) {
    companion object : SafeConstructor<AccessHash, String>() {
        override val displayName: String by wrapperTypeName()
        const val SIZE = 128

        context(ValidationFailureHandler)
        override fun create(value: String): AccessHash {
            return when (value.length) {
                0 -> onFail(FailureMessage.ofBlank())
                SIZE -> AccessHash(value)
                else -> onFail(FailureMessage.ofSize(SIZE))
            }
        }
    }
}