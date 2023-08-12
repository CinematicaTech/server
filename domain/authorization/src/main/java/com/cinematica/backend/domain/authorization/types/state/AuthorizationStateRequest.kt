package com.cinematica.backend.domain.authorization.types.state

import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest
import kotlinx.serialization.Serializable
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.i18n.mapToMessage
import org.valiktor.validate
import java.util.Locale

@Serializable
data class AuthorizationStateRequest(
    val email: String
) {
    val validationErrorMessage: String? = runCatching {
        validate(this) {
            validate(AuthorizationStateRequest::email).isEmail()
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