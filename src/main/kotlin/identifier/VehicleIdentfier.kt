package org.dru128.identifier

import org.dru128.vehicle.Vehicle

interface VehicleIdentfier {
    fun onDetectVehicle(callback: (Vehicle) -> Unit)
}