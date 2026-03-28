package org.dru128.checkpoint

import org.dru128.access.AccessHandler
import org.dru128.barrier.Barrier
import org.dru128.barrier.BarrierDriver
import org.dru128.barrier.PlatformBarrier
import org.dru128.identifier.VehicleIdentifier
import org.dru128.log.Logger

interface ICheckpointFacadeBuilder {
    fun setId(id: String): ICheckpointFacadeBuilder
    fun setLogger(logger: Logger): ICheckpointFacadeBuilder
    fun setBarrierDriver(barrierDriver: BarrierDriver): ICheckpointFacadeBuilder
    fun setBarrier(barrier: Barrier): ICheckpointFacadeBuilder
    fun setVehicleIdentifier(vehicleIdentifier: VehicleIdentifier): ICheckpointFacadeBuilder
    fun setAccessHandler(accessHandler: AccessHandler): ICheckpointFacadeBuilder
    fun setBarrierOpenDuration(duration: Long): ICheckpointFacadeBuilder

    fun build(): CheckpointFacade
}

class CheckpointFacadeBuilder: ICheckpointFacadeBuilder {
    private var checkpointId: String? = null
    private var checkpointLogger: Logger? = null
    private var checkpointBarrierDriver: BarrierDriver? = null
    private var checkpointBarrier: Barrier? = null
    private var checkpointVehicleIdentifier: VehicleIdentifier? = null
    private var checkpointAccessHandler: AccessHandler? = null
    private var checkpointOpenDuration: Long? = null

    override fun setId(id: String): ICheckpointFacadeBuilder {
        checkpointId = id
        println("[BUILDER] setId=$id")
        return this
    }

    override fun setLogger(logger: Logger): ICheckpointFacadeBuilder {
        checkpointLogger = logger
        println("[BUILDER] setLogger=$logger")
        return this
    }

    override fun setBarrierDriver(barrierDriver: BarrierDriver): ICheckpointFacadeBuilder {
        checkpointBarrierDriver = barrierDriver
        println("[BUILDER] setBarrierDriver=$barrierDriver")
        return this
    }

    override fun setBarrier(barrier: Barrier): ICheckpointFacadeBuilder {
        checkpointBarrier = barrier
        println("[BUILDER] setBarrier=$barrier")
        return this
    }

    override fun setVehicleIdentifier(vehicleIdentifier: VehicleIdentifier): ICheckpointFacadeBuilder {
        checkpointVehicleIdentifier = vehicleIdentifier
        println("[BUILDER] setVehicleIdentifier=$vehicleIdentifier")
        return this
    }

    override fun setBarrierOpenDuration(duration: Long): ICheckpointFacadeBuilder {
        checkpointOpenDuration = duration
        println("[BUILDER] setBarrierOpenDuration=$duration")
        return this
    }

    override fun setAccessHandler(accessHandler: AccessHandler): ICheckpointFacadeBuilder {
        checkpointAccessHandler = accessHandler
        println("[BUILDER] setAccessHandler=$accessHandler")
        return this
    }

    override fun build(): CheckpointFacade {
        val id = requireNotNull(checkpointId) { "Checkpoint id is required" }
        val logger = requireNotNull(checkpointLogger) { "Logger is required" }
        val barrier = requireNotNull(checkpointBarrier) { "Barrier is required" }
        val vehicleIdentifier = requireNotNull(checkpointVehicleIdentifier) { "VehicleIdentifier is required" }
        val accessHandler = requireNotNull(checkpointAccessHandler) { "AccessHandler is required" }

        if (barrier is PlatformBarrier && checkpointBarrierDriver != null) {
            barrier.driver = checkpointBarrierDriver!!
        }
        println("[BUILDER] SUCCESS. CheckpointId = $checkpointId")

        return CheckpointFacade(
            id = id,
            vehicleIdentifier = vehicleIdentifier,
            barrier = barrier,
            accessHandler = accessHandler,
            openDuration = checkpointOpenDuration,
            logger = logger,
        )
    }
}
