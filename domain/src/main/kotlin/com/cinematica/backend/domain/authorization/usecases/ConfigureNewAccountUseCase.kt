package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.repositories.UsersRepository

class ConfigureNewAccountUseCase(
    private val usersRepository: UsersRepository
): UseCase {
}