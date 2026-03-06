package org.dru128.identifier

import org.dru128.vehicle.Vehicle
import org.dru128.mock.IncomingVehicles

class ControllerNFC: VehicleIdentfier {
    override fun onDetectVehicle(callback: (Vehicle) -> Unit) {
       callback.invoke(IncomingVehicles.getRandomVehicle())
    }
}