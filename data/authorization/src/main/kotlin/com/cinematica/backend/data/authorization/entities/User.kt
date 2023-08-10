package com.cinematica.backend.data.authorization.entities

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val email: String,
    val password: String,
    // val salt: String,
    @BsonId val id: ObjectId = ObjectId()
)