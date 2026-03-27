package org.dru128.mock

import org.dru128.vehicle.Vehicle

class IncomingVehicleIterator(
    private val vehicleRows: Array<Pair<String, Vehicle.Type?>>,
) : Iterator<Vehicle> {
    private var currentIndex = 0

    override fun hasNext(): Boolean {
        return currentIndex < vehicleRows.size
    }

    override fun next(): Vehicle {
        val vehicleRow = vehicleRows[currentIndex++]
        return Vehicle(
            id = vehicleRow.first,
            type = vehicleRow.second,
        )
    }
}
