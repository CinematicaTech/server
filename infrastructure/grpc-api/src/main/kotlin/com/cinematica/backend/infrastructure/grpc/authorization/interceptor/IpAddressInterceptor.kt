package com.cinematica.backend.infrastructure.grpc.authorization.interceptor

import com.cinematica.backend.domain.authorization.types.metadata.value.ClientIpAddress
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcInterceptor
import com.cinematica.backend.infrastructure.grpc.common.validation.createOrStatus
import io.grpc.Grpc
import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.kotlin.CoroutineContextServerInterceptor
import kotlin.coroutines.CoroutineContext

class IpAddressInterceptor : CoroutineContextServerInterceptor(), GrpcInterceptor {
    override fun coroutineContext(call: ServerCall<*, *>, headers: Metadata): CoroutineContext {
        return SessionContext(
            ipAddress = ClientIpAddress.createOrStatus(call.attributes.get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR).toString()),
        )
    }
}