package com.cinematica.backend.infrastructure.grpc.authorization.mapper

import com.cinematica.backend.authorization.types.AuthorizationOuterClass
import com.cinematica.backend.authorization.types.authorization
import com.cinematica.backend.domain.authorization.types.Authorization
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcMapper

class GrpcAuthorizationsMapper : GrpcMapper {
    fun toGrpcAuthorization(authorization: Authorization): AuthorizationOuterClass.Authorization {
        return authorization {
            accessToken = authorization.accessHash.string
            refreshToken = authorization.refreshAccessHash.string
        }
    }
}