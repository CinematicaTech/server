package com.cinematica.backend.domain.authorization.types.signup

import kotlinx.serialization.Serializable
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.i18n.mapToMessage
import org.valiktor.validate
import java.util.Locale

@Serializable
data class SignUpRequest(
    val email: String,
    val password: String
) {
    val validationErrorMessage: String? = runCatching {
        validate(this) {
            validate(SignUpRequest::email).isEmail()
            validate(SignUpRequest::password).isNotBlank().hasSize(min = 6, max = 40)
        }
    }.exceptionOrNull()?.let { e ->
        if (e is ConstraintViolationException) {
            e.constraintViolations
                .mapToMessage(baseName = "messages", locale = Locale.ENGLISH)
                .map { "${it.property}: ${it.message}" }
                .first()
        } else {
            "Validation error occurred."
        }
    }
}