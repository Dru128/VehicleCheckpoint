package org.dru128.mock

import org.dru128.vehicle.Vehicle

class IncomingVehicleIterator(
    private val vehicles: List<Vehicle>,
): Iterator<Vehicle> {
    private var currentIndex = 0

    override fun hasNext(): Boolean {
        return currentIndex < vehicles.size
    }

    override fun next(): Vehicle {
        return vehicles[currentIndex++]
    }
}
