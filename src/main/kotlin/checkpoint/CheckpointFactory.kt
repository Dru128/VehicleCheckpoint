package org.dru128.checkpoint

import org.dru128.access.AccessHandler
import org.dru128.access.WhiteListAccessHandler
import org.dru128.barrier.*
import org.dru128.identifier.ANPRCameraAdapter
import org.dru128.identifier.VehicleIdentfier
import org.dru128.log.ConsoleLogger
import org.dru128.log.FileLogger
import org.dru128.log.Logger
import org.dru128.storage.PostgresWhiteList
import org.dru128.vehicle.SimpleVehicleNumberValidator

interface CheckpointFactory {
    fun createLogger(config: CheckpointConfig): Logger
    fun createBarrierDriver(logger: Logger): BarrierDriver
    fun createBarrier(driver: BarrierDriver): Barrier
    fun createVehicleIdentifier(logger: Logger): VehicleIdentfier
    fun createAccessHandler(logger: Logger): AccessHandler

    fun create(config: CheckpointConfig): CheckpointFacade
}

object LinuxCheckpointFactory: CheckpointFactory {
    override fun createLogger(config: CheckpointConfig): Logger {
        return config.loggerFilePath
            ?.let(::FileLogger)
            ?: ConsoleLogger()
    }

    override fun createBarrierDriver(logger: Logger): BarrierDriver {
        return LinuxBarrierDriver(logger)
    }

    override fun createBarrier(driver: BarrierDriver): Barrier {
        return BoomBarrier(id = "LBB-01", driver = driver)
    }

    override fun createVehicleIdentifier(logger: Logger): VehicleIdentfier {
        return ANPRCameraAdapter(logger)
    }

    override fun createAccessHandler(logger: Logger): AccessHandler {
        return WhiteListAccessHandler(
            whiteList = PostgresWhiteList,
            numberValidator = SimpleVehicleNumberValidator(),
            logger = logger,
        )
    }

    override fun create(config: CheckpointConfig): CheckpointFacade {
        val logger = createLogger(config)
        val barrierDriver = createBarrierDriver(logger)

        val id = "LCP-01"
        println("[LinuxCheckpointFactory] create CheckpointFacade id = $id")
        return CheckpointFacade(
            id = id,
            vehicleIdentfier = createVehicleIdentifier(logger),
            barrier = createBarrier(barrierDriver),
            accessHandler = createAccessHandler(logger),
            openDuration = config.openDuration,
            logger = logger,
        )
    }
}

object WindowsCheckpointFactory: CheckpointFactory {
    override fun createLogger(config: CheckpointConfig): Logger {
        return config.loggerFilePath
            ?.let(::FileLogger)
            ?: ConsoleLogger()
    }

    override fun createBarrierDriver(logger: Logger): BarrierDriver {
        return WindowsBarrierDriver(logger)
    }

    override fun createBarrier(driver: BarrierDriver): Barrier {
        return BoomBarrier(id = "WBB-01", driver = driver)
    }

    override fun createVehicleIdentifier(logger: Logger): VehicleIdentfier {
        return ANPRCameraAdapter(logger)
    }

    override fun createAccessHandler(logger: Logger): AccessHandler {
        return WhiteListAccessHandler(
            whiteList = PostgresWhiteList,
            numberValidator = SimpleVehicleNumberValidator(),
            logger = logger,
        )
    }

    override fun create(config: CheckpointConfig): CheckpointFacade {
        val logger = createLogger(config)
        val barrierDriver = createBarrierDriver(logger)

        val id = "WCP-01"
        println("[WindowsCheckpointFactory] create CheckpointFacade id = $id")
        return CheckpointFacade(
            id = id,
            vehicleIdentfier = createVehicleIdentifier(logger),
            barrier = createBarrier(barrierDriver),
            accessHandler = createAccessHandler(logger),
            openDuration = config.openDuration,
            logger = logger,
        )
    }
}