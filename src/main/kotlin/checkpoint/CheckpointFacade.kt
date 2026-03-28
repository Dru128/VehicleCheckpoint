package org.dru128.checkpoint

import org.dru128.AccessResult
import org.dru128.access.AccessHandler
import org.dru128.access.WhiteListAccessHandler
import org.dru128.barrier.Barrier
import org.dru128.barrier.BoomBarrier
import org.dru128.barrier.WindowsBarrierDriver
import org.dru128.identifier.ANPRCameraAdapter
import org.dru128.identifier.VehicleIdentifier
import org.dru128.log.Logger
import org.dru128.storage.PostgresWhiteList
import org.dru128.vehicle.SimpleVehicleNumberValidator
import org.dru128.vehicle.Vehicle

class CheckpointFacade(
    val id: String,
    override var vehicleIdentifier: VehicleIdentifier,
    barrier: Barrier,
    var accessHandler: AccessHandler,
    var openDuration: Long?,
    var logger: Logger,
) : Checkpoint {
    private var currentBarrier: Barrier = barrier

    override val barrier: Barrier
        get() = currentBarrier

    constructor(id: String, logger: Logger): this(
        id = id,
        logger = logger,
        vehicleIdentifier = ANPRCameraAdapter(logger),
        barrier = BoomBarrier(id = "BB-01", driver = WindowsBarrierDriver(logger)),
        accessHandler = WhiteListAccessHandler(
            whiteList = PostgresWhiteList,
            numberValidator = SimpleVehicleNumberValidator(),
            logger = logger,
        ),
        openDuration = 2000,
    )

    override var isActive: Boolean = false
        private set

    override fun setVehicleIdetifier(vehicleIdentifier: VehicleIdentifier) {
        logger.log("CheckpointFacade", "checkPointId=$id new vehicleIdetifier = ${vehicleIdentifier.javaClass}")
        this.vehicleIdentifier = vehicleIdentifier
    }

    override fun setBarrier(barrier: Barrier) {
        logger.log("CheckpointFacade", "checkPointId=$id new barrier = ${barrier.javaClass}")
        this.currentBarrier = barrier
    }

    override fun start() {
        if (isActive) return
        isActive = true
        logger.log("CheckpointFacade", "checkPointId=$id started facade")
        logger.log(
            "CheckpointFacade",
            "configuration: vehicle = ${vehicleIdentifier.javaClass.name}, barrier = ${barrier.javaClass}, accessHandler = ${accessHandler.javaClass}"
        )
        accessHandler()
    }

    override fun stop() {
        if (!isActive) return
        isActive = false
        logger.log("CheckpointFacade", "checkPointId=$id stopped facade")
    }

    override fun accessHandler() {
        vehicleIdentifier.onDetectVehicle { vehicle ->
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
            currentBarrier.open(vehicle.openDurationOrDefault(openDuration))
        }

        return result
    }
}
