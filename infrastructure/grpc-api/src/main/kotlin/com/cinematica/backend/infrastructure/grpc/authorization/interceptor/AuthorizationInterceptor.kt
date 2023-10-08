package com.cinematica.backend.infrastructure.grpc.authorization.interceptor

import com.cinematica.backend.domain.authorization.types.metadata.value.ClientIpAddress
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcInterceptor
import com.cinematica.backend.infrastructure.grpc.common.validation.createOrStatus
import io.grpc.Grpc
import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.kotlin.CoroutineContextServerInterceptor
import com.cinematica.backend.infrastructure.grpc.authorization.provider.AuthorizationProvider
import kotlin.coroutines.CoroutineContext

class AuthorizationInterceptor(
    private val authorizationProvider: AuthorizationProvider,
) : CoroutineContextServerInterceptor(), GrpcInterceptor {
    companion object {
        private val ACCESS_TOKEN_METADATA_KEY: Metadata.Key<String> =
            Metadata.Key.of("access-token", Metadata.ASCII_STRING_MARSHALLER)
    }

    override fun coroutineContext(call: ServerCall<*, *>, headers: Metadata): CoroutineContext {
        return AuthorizationContext(
            accessHash = headers.get(ACCESS_TOKEN_METADATA_KEY),
            provider = authorizationProvider,
            ipAddress = ClientIpAddress.createOrStatus(call.attributes.get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR).toString()),
        )
    }
}