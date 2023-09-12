package com.cinematica.exception.handling

import kotlinx.serialization.Serializable

@Serializable
data class ExceptionResponse(
    val message: String,
    val code: Int
)