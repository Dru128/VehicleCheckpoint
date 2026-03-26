package org.dru128.checkpoint

import org.dru128.AccessResult
import org.dru128.access.AccessHandler
import org.dru128.barrier.Barrier
import org.dru128.identifier.VehicleIdentfier
import org.dru128.log.Logger
import org.dru128.log.VehicleLogger
import org.dru128.vehicle.Vehicle

class CheckpointFacade(
    private val id: String,
    vehicleIdentfier: VehicleIdentfier,
    barrier: Barrier,
    private val accessHandler: AccessHandler,
    private val openDuration: Long?,
    private val logger: Logger,
) : Checkpoint {
    override var vehicleIdentfier: VehicleIdentfier = vehicleIdentfier
        private set

    override var barrier: Barrier = barrier
        private set

    override var isActive: Boolean = false
        private set

    override fun setVehicleIdetifier(vehicleIdentfier: VehicleIdentfier) {
        logger.log("CheckpointFacade", "checkPointId=$id new vehicleIdetifier = ${vehicleIdentfier.javaClass}")
        this.vehicleIdentfier = vehicleIdentfier
    }

    override fun setBarrier(barrier: Barrier) {
        logger.log("CheckpointFacade", "checkPointId=$id new barrier = ${barrier.javaClass}")
        this.barrier = barrier
    }

    override fun start() {
        if (isActive) return
        isActive = true
        logger.log("CheckpointFacade", "checkPointId=$id started facade")
        logger.log(
            "CheckpointFacade",
            "configuration: vehicle = ${vehicleIdentfier.javaClass.name}, barrier = ${barrier.javaClass}, accessHandler = ${accessHandler.javaClass}"
        )
        accessHandler()
    }

    override fun stop() {
        if (!isActive) return
        isActive = false
        logger.log("CheckpointFacade", "checkPointId=$id stopped facade")
    }

    override fun accessHandler() {
        vehicleIdentfier.onDetectVehicle { vehicle ->
            process(vehicle)
        }
    }

    fun process(vehicle: Vehicle): AccessResult? {
        if (!isActive) return null

        logger.log(
            "CheckpointFacade",
            "checkPointId=$id process vehicle=$vehicle"
        )

        val result = accessHandler.handle(vehicle)

        if (result == AccessResult.ACCESS_APPROVED) {
            barrier.open(vehicle.openDurationOrDefault(openDuration))
        }

        return result
    }
}
