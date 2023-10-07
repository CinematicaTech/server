package com.cinematica.backend.domain.authorization.types.value

import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class RefreshHash private constructor(val string: String) {
    companion object : SafeConstructor<RefreshHash, String>() {
        override val displayName: String by wrapperTypeName()
         const val SIZE = 128

        context(ValidationFailureHandler)
        override fun create(value: String): RefreshHash {
            return when (value.length) {
                0 -> onFail(FailureMessage.ofBlank())
                SIZE -> RefreshHash(value)
                else -> onFail(FailureMessage.ofSize(SIZE))
            }
        }
    }
}