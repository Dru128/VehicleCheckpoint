package org.dru128.access

import org.dru128.AccessResult
import org.dru128.log.Logger
import org.dru128.vehicle.Vehicle
import java.time.LocalTime

class TimeAccessProxy(
    private val accessHandler: AccessHandler,
    private val startTime: LocalTime,
    private val endTime: LocalTime,
    private val currentTimeProvider: () -> LocalTime = { LocalTime.now() },
    val logger: Logger,
) : AccessHandler {
    override fun handle(vehicle: Vehicle): AccessResult {
        val currentTime = currentTimeProvider()
        val isAllowedNow = !currentTime.isBefore(startTime) && !currentTime.isAfter(endTime)

        if (!isAllowedNow) {
            logger.log("TimeAccessProxy", "ACCESS_DENIED_BY_TIME, " +
                    "access time range = (${startTime.hour}:${startTime.minute} - " +
                    "${endTime.hour}:${endTime.minute}) " +
                    ",vehicle = $vehicle")
            return AccessResult.ACCESS_DENIED_BY_TIME
        }

        return accessHandler.handle(vehicle)
    }
}
