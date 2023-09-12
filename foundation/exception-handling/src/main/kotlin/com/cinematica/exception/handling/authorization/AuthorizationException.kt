package com.cinematica.exception.handling.authorization

sealed class AuthorizationException private constructor(message: String) : Exception(message) {

    /**
     * An exception that occurs during user registration.
     * @param message The error message.
     */
    class SignUpException(message: String) : AuthorizationException(message)

    /**
     * An exception that occurs during user sign-in.
     * @param message The error message.
     */
    class SignInException(message: String) : AuthorizationException(message)

    /**
     * An exception that occurs when getting authorization state.
     * @param message The error message.
     */
    class GetAuthorizationStateException(message: String) : AuthorizationException(message)

    companion object {
        /**
         * Factory method to create a specific exception type.
         * @param message The error message.
         * @param type The type of authorization exception to create.
         */
        fun createException(message: String, type: ExceptionType): AuthorizationException {
            return when (type) {
                ExceptionType.SIGN_UP -> SignUpException(message)
                ExceptionType.SIGN_IN -> SignInException(message)
                ExceptionType.GET_STATE -> GetAuthorizationStateException(message)
            }
        }
    }
}

enum class ExceptionType {
    SIGN_UP, SIGN_IN, GET_STATE
}