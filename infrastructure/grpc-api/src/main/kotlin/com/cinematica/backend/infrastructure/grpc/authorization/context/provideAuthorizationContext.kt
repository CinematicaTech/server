package com.cinematica.backend.infrastructure.grpc.authorization.context

import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.foundation.authorization.Authorized
import com.cinematica.backend.foundation.authorization.AuthorizedContext
import com.cinematica.backend.foundation.authorization.Scope
import com.cinematica.backend.foundation.authorization.authorizationProvider
import com.cinematica.backend.foundation.authorization.types.AuthorizedId
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcService
import io.grpc.Status
import io.grpc.StatusException
import com.cinematica.backend.infrastructure.grpc.authorization.interceptor.AuthorizationContext
import com.cinematica.backend.infrastructure.grpc.common.validation.createOrStatus
import kotlin.coroutines.coroutineContext

/**
 * Provides an authorization context based on the access token provided in the request headers.
 * The context is only provided if the access token is valid and the user has the necessary scopes.
 *
 * @param constraint a function that takes a list of scopes and returns a boolean indicating whether
 * the user has the necessary scopes.
 * @param block a suspend function that provides an authorized context.
 * @throws StatusException if the user is not authenticated or does not have the necessary scopes.
 */
context (GrpcService)
@Throws(StatusException::class)
suspend inline fun <reified T : Scope, R> provideAuthorizationContext(
    constraint: (List<Scope>) -> Boolean = { scopes -> scopes.any { it is T || it is Scope.All } },
    block: context(AuthorizedContext<T>) () -> R,
): R {
    val authorizationContext = coroutineContext[AuthorizationContext]!!
    val hashValue = authorizationContext.accessHash ?: throw StatusException(Status.UNAUTHENTICATED)
    val accessHash = AccessHash.createOrStatus(hashValue)

    return authorizationProvider(
        provider = {
            authorizationContext.provider.provide(accessHash)?.takeIf {
                constraint(it.scopes)
            }?.let {
                Authorized(AuthorizedId(it.userId.long), scopes = it.scopes)
            }
        },
        onFailure = { throw StatusException(Status.UNAUTHENTICATED) },
        block = block,
    )
}