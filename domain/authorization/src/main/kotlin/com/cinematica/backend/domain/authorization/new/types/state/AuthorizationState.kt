package com.cinematica.backend.domain.authorization.new.types.state

sealed class AuthorizationState(val value: String) {
    data object SignUp : AuthorizationState("sign_up")

    data object SignIn : AuthorizationState("sign_in")
}