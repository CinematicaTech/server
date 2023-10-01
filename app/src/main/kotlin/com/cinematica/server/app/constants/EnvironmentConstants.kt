package com.cinematica.server.app.constants

internal object EnvironmentConstants {
    private const val TIME_MATES_PREFIX = "CINEMATICA"

    const val GRPC_PORT = "${TIME_MATES_PREFIX}_GRPC_PORT"
    const val DATABASE_URL = "DATABASE_URL"
    const val DATABASE_USER = "DATABASE_USER"
    const val DATABASE_PASSWORD = "DATABASE_PASSWORD"
}