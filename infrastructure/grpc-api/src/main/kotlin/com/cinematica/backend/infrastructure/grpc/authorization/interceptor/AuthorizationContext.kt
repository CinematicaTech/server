package com.cinematica.backend.infrastructure.grpc.authorization.interceptor

import com.cinematica.backend.domain.authorization.types.metadata.value.ClientIpAddress
import com.cinematica.backend.infrastructure.grpc.authorization.provider.AuthorizationProvider
import kotlin.coroutines.CoroutineContext

data class AuthorizationContext(
    val accessHash: String?,
    val provider: AuthorizationProvider,
    val ipAddress: ClientIpAddress,
) : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<AuthorizationContext>

    override val key: CoroutineContext.Key<*> = Key
}