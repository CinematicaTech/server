package com.cinematica.backend.domain.authorization.types

data class AuthorizationState(
    val state: String
)

enum class AuthorizationMethod(val value: String) {
    SIGN_UP("sign_up"),
    SIGN_IN("sign_in")
}