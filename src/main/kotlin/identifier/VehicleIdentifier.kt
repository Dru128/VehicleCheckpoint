package org.dru128.identifier

import org.dru128.observer.Subject
import org.dru128.vehicle.Vehicle

interface VehicleIdentifier {
    fun startVehicleDetection(vehicleSubject: Subject<Vehicle?>)
}
