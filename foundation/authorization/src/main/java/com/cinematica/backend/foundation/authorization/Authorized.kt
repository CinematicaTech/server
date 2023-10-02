package com.cinematica.backend.foundation.authorization

import com.cinematica.backend.foundation.authorization.types.AuthorizedId

public class Authorized(
    public val authorizedId: AuthorizedId,
    public val scopes: List<Scope>,
)