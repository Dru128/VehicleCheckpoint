package org.dru128.access

import org.dru128.AccessResult
import org.dru128.vehicle.Vehicle

interface AccessHandler {
    fun handle(vehicle: Vehicle): AccessResult
}