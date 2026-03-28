package org.dru128.checkpoint

import org.dru128.barrier.Barrier
import org.dru128.identifier.VehicleIdentifier

interface Checkpoint {
    val isActive: Boolean
    val barrier: Barrier
    val vehicleIdentifier: VehicleIdentifier

    fun setVehicleIdetifier(vehicleIdentifier: VehicleIdentifier)
    fun setBarrier(barrier: Barrier)
    fun start()
    fun stop()
    fun accessHandler()
}
