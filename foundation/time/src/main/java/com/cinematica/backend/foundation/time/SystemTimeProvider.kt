package com.cinematica.backend.foundation.time

import com.cinematica.backend.foundation.validation.createOrThrowInternally
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

public class SystemTimeProvider(
    timeZone: ZoneId = ZoneId.systemDefault(),
) : TimeProvider {
    private val clock = Clock.system(timeZone)

    override fun provide(): UnixTime {
        val instant = Instant.now(clock)
        return UnixTime.createOrThrowInternally(instant.toEpochMilli())
    }
}