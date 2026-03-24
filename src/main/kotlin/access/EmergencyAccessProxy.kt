package org.dru128.access

import org.dru128.AccessResult
import org.dru128.log.Logger
import org.dru128.vehicle.Vehicle

// Экстренные службы пропускаются без проверки доступа
class EmergencyAccessProxy(
    private val accessHandler: AccessHandler,
    val logger: Logger,
) : AccessHandler {
    override fun handle(vehicle: Vehicle): AccessResult {
        if (vehicle.type == Vehicle.Type.EMERGENCY) {
            logger.log("EmergencyAccessProxy", "ACCESS_APPROVED for Emergency without verification, vehicle = $vehicle")
            return AccessResult.ACCESS_APPROVED
        }
        return accessHandler.handle(vehicle)
    }
}