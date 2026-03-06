package org.dru128

import org.dru128.access.WhiteListAccessHandler
import org.dru128.barrier.BoomBarrier
import org.dru128.checkpoint.CheckpointConfig
import org.dru128.checkpoint.LoggerProxyCheckpoint
import org.dru128.checkpoint.SimpleCheckPoint
import org.dru128.identifier.ANPRCamera
import org.dru128.log.ConsoleLogger
import org.dru128.log.ConsoleVehicleLogger
import org.dru128.log.FileLogger
import org.dru128.storage.PostgressWhiteList
import org.dru128.vehicle.SimpleVehicleNumberValidator

fun main() {
    val config = CheckpointConfig(
        openDuration = 2_000,
        loggerFilePath = "logs/checkpoint.txt",
        vehicleLoggerFilePath = "logs/vehicle.txt",
    )

    val logger = if (config.loggerFilePath != null) {
        FileLogger(config.loggerFilePath)
    } else {
        ConsoleLogger()
    }

    val checkPoint = SimpleCheckPoint(
        id = "CP-01",
        vehicleIdentfier = ANPRCamera(),
        barrier = BoomBarrier(logger = logger),
        accessHandler = WhiteListAccessHandler(
            whiteList = PostgressWhiteList,
            numberValidator = SimpleVehicleNumberValidator(),
        ),
        openDuration = config.openDuration,
        logger = logger,
    )

    // прокси КПП, добавляющий логирование событий факта проезда/отказа в доступе и статусов
    val loggedCheckPoint = LoggerProxyCheckpoint(
        checkPoint = checkPoint,
        logger = ConsoleVehicleLogger(),
    )

    loggedCheckPoint.start()
}
