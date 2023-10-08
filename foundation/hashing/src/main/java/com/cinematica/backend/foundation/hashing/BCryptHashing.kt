package com.cinematica.backend.foundation.hashing

import org.mindrot.jbcrypt.BCrypt

class BCryptHashing : HashingRepository {
    override fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    override fun validPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }
}

fun main() {
    val password = "d122shda12282hjaska_-32132"
    val wrongPassword = "d122shda122"
    val bcrypt = BCryptHashing()

    // Хешуємо пароль
    val hashedPassword = bcrypt.hashPassword(password)

    // Перевіряємо правильність паролю
    val isValid = bcrypt.validPassword(password, hashedPassword)

    println("Оригінальний пароль: $password")
    println("Захешований пароль: $hashedPassword")
    println("Перевірка паролю: $isValid")

    // Перевірка неправильного паролю
    val isInvalid = bcrypt.validPassword(wrongPassword, hashedPassword)
    println("Перевірка неправильного паролю: $isInvalid")
}