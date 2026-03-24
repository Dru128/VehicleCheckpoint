package org.dru128.access

import org.dru128.AccessResult
import org.dru128.log.Logger
import org.dru128.vehicle.Vehicle
import org.dru128.vehicle.VehicleNumberValidator

class WhiteListAccessHandler(
    val whiteList: WhiteList,
    val numberValidator: VehicleNumberValidator,
    val logger: Logger,
): AccessHandler {
    override fun handle(vehicle: Vehicle): AccessResult {
        if (vehicle.id.isNullOrEmpty()) {
            return AccessResult.NOT_FOUND_ID
        }

        val isValid = numberValidator.validate(vehicle.id)

        if (isValid) {
            val isAllowed = whiteList.isAllowed(vehicle.id)

            if (isAllowed) {
                logger.log("WhiteListAccessHandler", "ACCESS_APPROVED by white list, vehicle = $vehicle")
                return AccessResult.ACCESS_APPROVED
            } else {
                logger.log("WhiteListAccessHandler", "ACCESS_DENIED. vehicle = $vehicle")
                return AccessResult.ACCESS_DENIED
            }
        } else {
            logger.log("WhiteListAccessHandler", "Invalid number! vehicle = $vehicle")
            return AccessResult.BAD_ID
        }
    }
}