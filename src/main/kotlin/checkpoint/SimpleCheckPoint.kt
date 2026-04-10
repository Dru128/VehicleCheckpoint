package org.dru128.checkpoint

import org.dru128.AccessResult
import org.dru128.access.AccessHandler
import org.dru128.barrier.Barrier
import org.dru128.identifier.VehicleIdentifier
import org.dru128.log.Logger

/*class SimpleCheckPoint(
    val id: String,
    vehicleIdentifier: VehicleIdentifier,
    barrier: Barrier,
    val accessHandler: AccessHandler,
    val openDuration: Long?,
    val logger: Logger,
): Checkpoint {
    override var vehicleIdentifier: VehicleIdentifier = vehicleIdentifier
        private set
    private var currentBarrier: Barrier = barrier

    override val barrier: Barrier
        get() = currentBarrier

    override var isActive: Boolean = false
        private set

    override fun setVehicleIdetifier(vehicleIdentifier: VehicleIdentifier) {
        logger.log("SimpleCheckPoint", "checkPointId=$id new vehicleIdetifier = ${vehicleIdentifier.javaClass}")
        this.vehicleIdentifier = vehicleIdentifier
    }

    override fun setBarrier(barrier: Barrier) {
        logger.log("SimpleCheckPoint", "checkPointId=$id new barrier = ${barrier.javaClass}")
        this.currentBarrier = barrier
    }

    override fun start() {
        if (isActive) return
        logger.log("SimpleCheckPoint", "checkPointId=$id Started checkpoint with id=$id")
        logger.log("SimpleCheckPoint", "configuration: vehicle = ${vehicleIdentifier.javaClass}, " +
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
        vehicleIdentifier.startVehicleDetection { vehicle ->
            if (isActive) {
                val result = accessHandler.handle(vehicle)
                if (result == AccessResult.ACCESS_APPROVED) {
                    barrier.open(duration = openDuration)
                }
            }
        }
    }
}*/
