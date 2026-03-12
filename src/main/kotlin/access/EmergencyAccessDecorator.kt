package org.dru128.access

import org.dru128.AccessResult
import org.dru128.vehicle.Vehicle

// Экстренные службы пропускаются без проверки доступа
class EmergencyAccessDecorator(
    private val accessHandler: AccessHandler,
) : AccessHandler {
    override fun handle(vehicle: Vehicle): AccessResult {
        if (vehicle.type == Vehicle.Type.EMERGENCY) {
            return AccessResult.ACCESS_APPROVED
        }
        return accessHandler.handle(vehicle)
    }
}