package com.cinematica.backend.infrastructure.grpc.common.validation

import com.cinematica.backend.foundation.validation.SafeConstructor
import com.cinematica.backend.foundation.validation.ValidationFailureHandler
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcContext
import io.grpc.Status
import io.grpc.StatusException

/**
 * A [ValidationFailureHandler] that always throws a [StatusException] with a
 * [Status.FAILED_PRECONDITION] status code and a description of the validation failure message.
 *
 * This should be used on top of gRPC calls.
 */
val ValidationFailureHandler.Companion.ALWAYS_STATUS_EXCEPTION by lazy {
    ValidationFailureHandler { failure ->
        throw StatusException(Status.FAILED_PRECONDITION.withDescription(failure.string))
    }
}

/**
 * Extension function on [SafeConstructor] that creates an instance of [T] using the given [w] input.
 *
 * If the validation fails, a [StatusException] will be thrown with a [Status.FAILED_PRECONDITION]
 * status code and a description of the validation failure message.
 *
 * @param w the input to pass to the [SafeConstructor]
 * @return the created instance of [T]
 */
context (GrpcContext)
fun <T, W> SafeConstructor<T, W>.createOrStatus(
    w: W,
): T = with(ValidationFailureHandler.ALWAYS_STATUS_EXCEPTION) {
    create(w)
}