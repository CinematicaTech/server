package com.cinematica.backend.domain.authorization.types

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class UserDomain(
    val email: String,
    val password: String,
    val salt: String,
    @BsonId val id: ObjectId = ObjectId()
)