package org.dru128.checkpoint

import org.dru128.barrier.Barrier
import org.dru128.identifier.VehicleIdentfier

interface Checkpoint {
    val isActive: Boolean
    val barrier: Barrier
    val vehicleIdentfier: VehicleIdentfier

    fun setVehicleIdetifier(vehicleIdentfier: VehicleIdentfier)
    fun setBarrier(barrier: Barrier)
    fun start()
    fun stop()
    fun accessHandler()
}
