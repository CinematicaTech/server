package com.cinematica.backend.domain.users.types.value

import com.cinematica.backend.domain.users.types.value.UserId.Companion.asUserId
import com.cinematica.backend.foundation.authorization.AuthorizedContext
import com.cinematica.backend.foundation.authorization.types.AuthorizedId
import com.cinematica.backend.foundation.validation.FailureMessage
import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.foundation.validation.reflection.wrapperTypeName

@JvmInline
value class UserId private constructor(val long: Long) {
    companion object : SafeConstructor<UserId, Long>() {
        override val displayName: String by wrapperTypeName()

        context(ValidationFailureHandler)
        override fun create(value: Long): UserId {
            return when {
                value >= 0 -> UserId(value)
                else -> onFail(FailureMessage.ofNegative())
            }
        }

        internal fun AuthorizedId.asUserId() = UserId(long)
    }
}

context(AuthorizedContext<*>)
val userId: UserId
    get() = authorization.authorizedId.asUserId()