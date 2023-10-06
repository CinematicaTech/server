package com.cinematica.backend.foundation.time

import com.cinematica.backend.foundation.validation.markers.InternalThrowAbility

public interface TimeProvider : InternalThrowAbility {
    /**
     * Provides current time in Unix format.
     */
    public fun provide(): UnixTime
}