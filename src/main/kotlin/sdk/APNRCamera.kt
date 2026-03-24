package org.dru128.sdk

import org.dru128.mock.IncomingVehicles

class APNRCamera : ExternalCameraSdk {
    override fun listenFrames(callback: (VehiclePhoto) -> Unit) {
        val vehicleIterator = IncomingVehicles.iterator()

        while (vehicleIterator.hasNext()) {
            val vehicle = vehicleIterator.next()
            val frameId = "frame${vehicle.hashCode()}"

            callback(
                VehiclePhoto(
                    frameId = "frame-$frameId",
                    uri = "mock://camera/$frameId.jpg",
                )
            )
        }
    }
}
