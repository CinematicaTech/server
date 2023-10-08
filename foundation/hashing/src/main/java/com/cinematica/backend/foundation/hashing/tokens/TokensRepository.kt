package com.cinematica.backend.foundation.hashing.tokens

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*

// Ключ для підпису токенів
private val secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

data class User(val userId: String, val username: String, val roles: List<String>, val time: Long)

fun generateAccessToken(user: User): String {
    //val expirationDate = Date(System.currentTimeMillis() + 15 * 60 * 1000)
    val expirationDate = Date(System.currentTimeMillis() + 8 * 1000)

    return Jwts.builder()
        .setSubject(user.userId)
        .claim("username", user.username)
        .claim("roles", user.roles.joinToString(","))
        .setExpiration(expirationDate)
        .signWith(secretKey)
        .compact()
}

fun validateAccessToken(accessToken: String): Boolean {
    try {
        val claims: Jws<Claims> = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(accessToken)

        // Перевірка часу дії токена
        val expirationDate = claims.body.expiration
        val currentDate = Date()
        return !expirationDate.before(currentDate) // Повертає true, якщо токен ще дійсний
    } catch (e: Exception) {
        // Помилка при перевірці токена, токен недійсний
        return false
    }
}

fun main(): Unit = runBlocking {
    // Створюємо об'єкт користувача з деякими даними
    val user = User("211", "user@example.com", emptyList(), System.currentTimeMillis())

    // Генеруємо access токен для користувача
    val accessToken = generateAccessToken(user)

    // Виводимо access токен
    println("Access Token: $accessToken")

    // Перевіряємо валідність access токена
    val isValidAccessToken = validateAccessToken(accessToken)

    if (isValidAccessToken) {
        println("Access Token is valid.")
    } else {
        println("Access Token is invalid.")
    }

    val isValidAccessToken2 = validateAccessToken(accessToken)

    if (isValidAccessToken2) {
        println("Access Token is valid. 2")
    } else {
        println("Access Token is invalid. 2")
    }
}
