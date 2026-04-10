package org.dru128.identifier

import org.dru128.log.Logger
import org.dru128.mock.IncomingVehicles
import org.dru128.observer.Subject
import org.dru128.sdk.APNRCameraPool
import org.dru128.vehicle.Vehicle

class ANPRCameraAdapter(
    val logger: Logger,
): VehicleIdentifier {
    override fun startVehicleDetection(vehicleSubject: Subject<Vehicle?>) {
        val camera = APNRCameraPool.pop()

        try {
            camera.listenFrames {
                val convertedVehicle = IncomingVehicles.getRandomVehicle()
                logger.log(
                    "ANPRCameraAdaptor",
                    "vehicle photo (uri = ${it.uri})\nconverted to vehicle = $convertedVehicle"
                )

                vehicleSubject.value = convertedVehicle
            }
        } finally {
            APNRCameraPool.push(camera)
        }
    }
}
