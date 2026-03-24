package org.dru128

import org.dru128.access.WhiteListAccessHandler
import org.dru128.barrier.BoomBarrier
import org.dru128.checkpoint.CheckpointConfig
import org.dru128.checkpoint.SimpleCheckPoint
import org.dru128.identifier.ANPRCameraAdapter
import org.dru128.log.ConsoleLogger
import org.dru128.log.FileLogger
import org.dru128.storage.PostgressWhiteList
import org.dru128.vehicle.SimpleVehicleNumberValidator


fun main() {
    // Конфиг содержащий общию информацию
    val config = CheckpointConfig(
        openDuration = 2_000,
        loggerFilePath = null,//"logs/checkpoint.txt",
        vehicleLoggerFilePath = "logs/vehicle.txt",
    )

    // Универсльный логгер
    val logger = if (config.loggerFilePath != null) {
        FileLogger(config.loggerFilePath)
    } else {
        ConsoleLogger()
    }

    val checkPoint = SimpleCheckPoint(
        id = "CP-01",
        vehicleIdentfier = ANPRCameraAdapter(logger), // извлекает из фото внешнего апи камеры номер и тип ТС
        barrier = BoomBarrier(id = "BB-01", logger = logger),
        accessHandler = WhiteListAccessHandler( // алгоритм доступа - белый список
            whiteList = PostgressWhiteList, // Имитация базы данных белого списка
            numberValidator = SimpleVehicleNumberValidator(), // реализация простой валидации номера
            logger = logger,
        ),
        openDuration = config.openDuration, // время на к-е открывается проезд
        logger = logger,
    )

    checkPoint.start()
}
