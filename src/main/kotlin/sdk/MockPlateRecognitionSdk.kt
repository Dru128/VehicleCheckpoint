package org.dru128.sdk

import org.dru128.mock.IncomingVehicles
import org.dru128.vehicle.Vehicle

class MockPlateRecognitionSdk : ExternalCameraSdk {
    private val frames = mutableMapOf<String, Vehicle>()

    override fun listenFrames(onFrameCaptured: (VehiclePhoto) -> Unit) {
        repeat(5) { index ->
            val vehicle = IncomingVehicles.getRandomVehicle()
            val frameId = "frame-$index"
            frames[frameId] = vehicle

            onFrameCaptured(
                VehiclePhoto(
                    frameId = frameId,
                    uri = "mock://camera/$frameId.jpg",
                )
            )
        }
    }

    override fun extractNumber(photo: VehiclePhoto): String? {
        return frames[photo.frameId]
            ?.id
            ?.trim()
            ?.uppercase()
            ?.ifBlank { null }
    }

    override fun resolveVehicleType(photo: VehiclePhoto): Vehicle.Type? {
        return frames[photo.frameId]?.type
    }
}
