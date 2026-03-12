package org.dru128.sdk

import org.dru128.mock.IncomingVehicles

class MockPlateRecognitionSdk : ExternalCameraSdk {
    override fun listenFrames(onFrameCaptured: (VehiclePhoto) -> Unit) {
        repeat(5) { index ->
            val vehicle = IncomingVehicles.getRandomVehicle()
            onFrameCaptured(
                VehiclePhoto(
                    frameId = "frame-${index + 1}",
                    rawPlateText = vehicle.id,
                    vehicleType = vehicle.type,
                )
            )
        }
    }

    override fun extractNumber(photo: VehiclePhoto): String? {
        return photo.rawPlateText
            ?.trim()
            ?.uppercase()
            ?.ifBlank { null }
    }
}
