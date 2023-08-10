package com.cinematica.backend.foundation.validation.exceptions

public class ValidationFailure(
    message: String,
) : Exception("Validation failed with message: $message")