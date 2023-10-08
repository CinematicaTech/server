package com.cinematica.backend.domain.authorization.types.metadata

import com.cinematica.backend.domain.authorization.types.metadata.value.ClientIpAddress
import com.cinematica.backend.domain.authorization.types.metadata.value.ClientName
import com.cinematica.backend.domain.authorization.types.metadata.value.ClientVersion

data class ClientMetadata(
    val clientName: ClientName,
    val clientVersion: ClientVersion,
    val clientIpAddress: ClientIpAddress,
)