package org.dru128.sdk

import org.dru128.vehicle.Vehicle

interface ExternalCameraSdk {
    fun listenFrames(onFrameCaptured: (VehiclePhoto) -> Unit)
    fun extractNumber(photo: VehiclePhoto): String?
    fun resolveVehicleType(photo: VehiclePhoto): Vehicle.Type?
}
