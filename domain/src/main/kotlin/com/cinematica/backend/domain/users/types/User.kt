package com.cinematica.backend.domain.users.types

import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.PasswordHash
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.domain.users.types.value.UserName

data class User(
    val userId: UserId,
    val email: EmailAddress,
    val password: PasswordHash,
    val username: UserName
)