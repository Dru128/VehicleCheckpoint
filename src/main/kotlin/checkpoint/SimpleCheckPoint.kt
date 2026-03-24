package org.dru128.checkpoint

import org.dru128.AccessResult
import org.dru128.access.AccessHandler
import org.dru128.barrier.Barrier
import org.dru128.identifier.VehicleIdentfier
import org.dru128.log.Logger

class SimpleCheckPoint(
    val id: String,
    vehicleIdentfier: VehicleIdentfier,
    barrier: Barrier,
    val accessHandler: AccessHandler,
    val openDuration: Long?,
    val logger: Logger,
): Checkpoint {
    override var vehicleIdentfier: VehicleIdentfier = vehicleIdentfier
        private set
    override var barrier: Barrier = barrier
        private set

    override var isActive: Boolean = false
        private set

    override fun setVehicleIdetifier(vehicleIdentfier: VehicleIdentfier) {
        logger.log("SimpleCheckPoint", "checkPointId=$id new vehicleIdetifier = ${vehicleIdentfier.javaClass}")
        this.vehicleIdentfier = vehicleIdentfier
    }

    override fun setBarrier(barrier: Barrier) {
        logger.log("SimpleCheckPoint", "checkPointId=$id new barrier = ${barrier.javaClass}")
        this.barrier = barrier
    }

    override fun start() {
        if (isActive) return
        logger.log("SimpleCheckPoint", "checkPointId=$id Started checkpoint with id=$id")
        logger.log("SimpleCheckPoint", "configuration: vehicle = ${vehicleIdentfier.javaClass}, " +
                "barrier = ${barrier.javaClass}, accessHandler = ${accessHandler.javaClass}")
        isActive = true
        accessHandler()
    }

    override fun stop() {
        if (!isActive) return
        logger.log("SimpleCheckPoint", "checkPointId = $id Stopped checkpoint with id = $id")
        isActive = false
    }

    override fun accessHandler() {
        vehicleIdentfier.onDetectVehicle { vehicle ->
            if (isActive) {
                val result = accessHandler.handle(vehicle)
                if (result == AccessResult.ACCESS_APPROVED) {
                    barrier.open(duration = openDuration)
                }
            }
        }
    }
}
