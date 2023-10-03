package com.cinematica.server.app.dependencies

val AppModule = listOf(
    DatabaseModule,
    AuthorizationsModule,
    UsersModule,
    GrpcServicesModule,
    CommonModule
)