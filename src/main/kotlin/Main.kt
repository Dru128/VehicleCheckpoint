package org.dru128

import org.dru128.access.WhiteListAccessHandler
import org.dru128.barrier.BoomBarrier
import org.dru128.barrier.LinuxBarrierDriver
import org.dru128.checkpoint.CheckpointConfig
import org.dru128.checkpoint.CheckpointFacade
import org.dru128.identifier.ANPRCameraAdapter
import org.dru128.log.ConsoleLogger
import org.dru128.log.FileLogger
import org.dru128.log.FileVehicleLogger
import org.dru128.storage.PostgressWhiteList
import org.dru128.vehicle.SimpleVehicleNumberValidator

fun main() {
    val config = CheckpointConfig(
        openDuration = 2_000,
        loggerFilePath = null,//"logs/checkpoint.txt",
        vehicleLoggerFilePath = null,
    )

    val logger = if (config.loggerFilePath != null) {
        FileLogger(config.loggerFilePath)
    } else {
        ConsoleLogger()
    }

    val barrierDriver = LinuxBarrierDriver(logger)

    val checkPoint = CheckpointFacade(
        id = "CP-01",
        vehicleIdentfier = ANPRCameraAdapter(logger),
        barrier = BoomBarrier(id = "BB-01", driver = barrierDriver),
        accessHandler = WhiteListAccessHandler(
            whiteList = PostgressWhiteList,
            numberValidator = SimpleVehicleNumberValidator(),
            logger = logger,
        ),
        openDuration = config.openDuration,
        logger = logger,
    )

    checkPoint.start()
}
