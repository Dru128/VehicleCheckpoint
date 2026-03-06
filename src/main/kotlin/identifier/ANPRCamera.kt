package org.dru128.identifier

import org.dru128.vehicle.Vehicle
import org.dru128.mock.IncomingVehicles

class ANPRCamera: VehicleIdentfier {
    override fun onDetectVehicle(callback: (Vehicle) -> Unit) {
        // имитация обнаружения транспорта
        repeat(5) {
            callback.invoke(IncomingVehicles.getRandomVehicle())
        }
    }
}