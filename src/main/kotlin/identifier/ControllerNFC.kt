package org.dru128.identifier

import org.dru128.log.Logger
import org.dru128.mock.IncomingVehicles
import org.dru128.observer.Subject
import org.dru128.vehicle.Vehicle

class ControllerNFC(
    val logger: Logger,
): VehicleIdentifier {
    override fun startVehicleDetection(vehicleSubject: Subject<Vehicle?>) {
        repeat(5) {
            val vehicle = IncomingVehicles.getRandomVehicle()
            vehicleSubject.value = vehicle
            logger.log("ANPRCameraAdaptor", "vehicle NFC code = ${vehicle.hashCode()}")
        }
    }
}
