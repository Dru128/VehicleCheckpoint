package org.dru128.checkpoint

import org.dru128.access.AccessHandler
import org.dru128.barrier.Barrier
import org.dru128.barrier.BarrierDriver
import org.dru128.barrier.PlatformBarrier
import org.dru128.identifier.VehicleIdentifier
import org.dru128.log.Logger

interface ICheckpointFacadeBuilder {
    fun buildId(id: String): ICheckpointFacadeBuilder
    fun buildLogger(logger: Logger): ICheckpointFacadeBuilder
    fun buildBarrierDriver(barrierDriver: BarrierDriver): ICheckpointFacadeBuilder
    fun buildBarrier(barrier: Barrier): ICheckpointFacadeBuilder
    fun buildVehicleIdentifier(vehicleIdentifier: VehicleIdentifier): ICheckpointFacadeBuilder
    fun buildAccessHandler(accessHandler: AccessHandler): ICheckpointFacadeBuilder
    fun buildBarrierOpenDuration(duration: Long): ICheckpointFacadeBuilder

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

    override fun buildId(id: String): ICheckpointFacadeBuilder {
        checkpointId = id
        println("[BUILDER] setId=$id")
        return this
    }

    override fun buildLogger(logger: Logger): ICheckpointFacadeBuilder {
        checkpointLogger = logger
        println("[BUILDER] setLogger=$logger")
        return this
    }

    override fun buildBarrierDriver(barrierDriver: BarrierDriver): ICheckpointFacadeBuilder {
        checkpointBarrierDriver = barrierDriver
        println("[BUILDER] setBarrierDriver=$barrierDriver")
        return this
    }

    override fun buildBarrier(barrier: Barrier): ICheckpointFacadeBuilder {
        checkpointBarrier = barrier
        println("[BUILDER] setBarrier=$barrier")
        return this
    }

    override fun buildVehicleIdentifier(vehicleIdentifier: VehicleIdentifier): ICheckpointFacadeBuilder {
        checkpointVehicleIdentifier = vehicleIdentifier
        println("[BUILDER] setVehicleIdentifier=$vehicleIdentifier")
        return this
    }

    override fun buildBarrierOpenDuration(duration: Long): ICheckpointFacadeBuilder {
        checkpointOpenDuration = duration
        println("[BUILDER] setBarrierOpenDuration=$duration")
        return this
    }

    override fun buildAccessHandler(accessHandler: AccessHandler): ICheckpointFacadeBuilder {
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
