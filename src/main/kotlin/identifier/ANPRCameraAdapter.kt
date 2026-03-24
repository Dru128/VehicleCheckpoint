package org.dru128.identifier

import org.dru128.log.Logger
import org.dru128.mock.IncomingVehicles
import org.dru128.sdk.APNRCamera
import org.dru128.vehicle.Vehicle

class ANPRCameraAdapter(
    val logger: Logger
): VehicleIdentfier {
    val anprCamera = APNRCamera()

    override fun onDetectVehicle(callback: (Vehicle) -> Unit) {
        anprCamera.listenFrames {
            val convertedVehicle = IncomingVehicles.getRandomVehicle()
            logger.log("ANPRCameraAdaptor", "vehicle photo (uri = ${it.uri})\n" +
                    "converted to vehicle = $convertedVehicle")

            callback.invoke(convertedVehicle)
        }
    }
}
