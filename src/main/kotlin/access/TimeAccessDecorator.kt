package org.dru128.access

import org.dru128.AccessResult
import org.dru128.vehicle.Vehicle
import java.time.LocalTime

class TimeAccessDecorator(
    private val accessHandler: AccessHandler,
    private val startTime: LocalTime,
    private val endTime: LocalTime,
    private val currentTimeProvider: () -> LocalTime = { LocalTime.now() },
) : AccessHandler {
    override fun handle(vehicle: Vehicle): AccessResult {
        val currentTime = currentTimeProvider()
        val isAllowedNow = !currentTime.isBefore(startTime) && !currentTime.isAfter(endTime)

        if (!isAllowedNow) {
            return AccessResult.ACCESS_DENIED_BY_TIME
        }

        return accessHandler.handle(vehicle)
    }
}
