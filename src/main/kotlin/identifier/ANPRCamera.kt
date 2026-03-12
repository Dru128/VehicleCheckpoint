package org.dru128.identifier

import org.dru128.mock.IncomingVehicles
import org.dru128.vehicle.Vehicle

class ANPRCamera: VehicleIdentfier {
    override fun onDetectVehicle(callback: (Vehicle) -> Unit) {
        val vehicleIterator = IncomingVehicles.iterator()

        while (vehicleIterator.hasNext()) {
            callback.invoke(vehicleIterator.next())
        }
    }
}
