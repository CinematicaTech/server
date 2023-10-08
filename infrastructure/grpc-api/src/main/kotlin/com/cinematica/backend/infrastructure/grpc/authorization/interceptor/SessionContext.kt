package com.cinematica.backend.infrastructure.grpc.authorization.interceptor

import com.cinematica.backend.domain.authorization.types.metadata.value.ClientIpAddress
import kotlin.coroutines.CoroutineContext

data class SessionContext(
    val ipAddress: ClientIpAddress,
) : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<SessionContext>

    override val key: CoroutineContext.Key<*> = Key
}