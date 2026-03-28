package org.dru128.identifier

import org.dru128.log.Logger
import org.dru128.vehicle.Vehicle
import org.dru128.mock.IncomingVehicles

class ControllerNFC(
    val logger: Logger,
): VehicleIdentifier {
    override fun onDetectVehicle(callback: (Vehicle) -> Unit) {
        repeat(5) {
            val vehicle = IncomingVehicles.getRandomVehicle()
            logger.log("ANPRCameraAdaptor", "vehicle NFC code = ${vehicle.hashCode()}")
            callback.invoke(vehicle)
        }
    }
}