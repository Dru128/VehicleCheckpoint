package org.dru128.access

import org.dru128.AccessResult
import org.dru128.vehicle.Vehicle
import org.dru128.vehicle.VehicleNumberValidator

class WhiteListAccessHandler(
    val whiteList: WhiteList,
    val numberValidator: VehicleNumberValidator,
): AccessHandler {
    override fun handle(vehicle: Vehicle): AccessResult {
        if (vehicle.id.isNullOrEmpty()) {
            return AccessResult.NOT_FOUND_ID
        }

        val isValid = numberValidator.validate(vehicle.id)

        if (isValid) {
            val isAllowed = whiteList.isAllowed(vehicle.id)

            if (isAllowed) {
                return AccessResult.ACCESS_APPROVED
            } else {
                return AccessResult.ACCESS_DENIED
            }
        } else {
            return AccessResult.BAD_ID
        }
    }
}