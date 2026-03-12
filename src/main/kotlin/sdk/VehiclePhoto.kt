package org.dru128.sdk

import org.dru128.vehicle.Vehicle

data class VehiclePhoto(
    val frameId: String,
    val rawPlateText: String?,
    val vehicleType: Vehicle.Type? = null,
)
