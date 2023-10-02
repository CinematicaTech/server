package com.cinematica.backend.foundation.validation.exceptions

internal class InternalValidationFailure(
    message: String,
) : Exception("Validation failed with message: $message")