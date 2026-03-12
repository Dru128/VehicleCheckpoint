package org.dru128

import org.dru128.access.EmergencyAccessDecorator
import org.dru128.access.TimeAccessDecorator
import org.dru128.access.WhiteListAccessHandler
import org.dru128.barrier.BoomBarrier
import org.dru128.barrier.CompositeBarrier
import org.dru128.barrier.GateBarrier
import org.dru128.checkpoint.CheckpointConfig
import org.dru128.checkpoint.LoggerProxyCheckpoint
import org.dru128.checkpoint.SimpleCheckPoint
import org.dru128.identifier.ANPRCamera
import org.dru128.log.ConsoleLogger
import org.dru128.log.ConsoleVehicleLogger
import org.dru128.log.FileLogger
import org.dru128.storage.PostgressWhiteList
import org.dru128.vehicle.SimpleVehicleNumberValidator
import java.time.LocalTime


fun main() {
    // Конфиг содержащий общию информацию
    val config = CheckpointConfig(
        openDuration = 2_000,
        loggerFilePath = "logs/checkpoint.txt",
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
        vehicleIdentfier = ANPRCamera(), // Идентифицирующее ТС устройство
        barrier = CompositeBarrier( // Композит барьеров (например несколько уровневая преграда)
            listOf(
                BoomBarrier(logger = logger),
                GateBarrier(logger = logger),
            )
        ), // Составной барьер: шлагбаум и ворота
        accessHandler = EmergencyAccessDecorator( // Декоратор пропускающий экстренные службы без проверки
            TimeAccessDecorator( // Декоратор доступа по времени
                accessHandler = WhiteListAccessHandler( // алгоритм доступа - белый список
                    whiteList = PostgressWhiteList, // Имитация базы данных белого списка
                    numberValidator = SimpleVehicleNumberValidator(), // реализация простой валидации номера
                ),
                startTime = LocalTime.of(7, 0),
                endTime = LocalTime.of(22, 0),
            ),
        ),
        openDuration = config.openDuration, // время на к-е открывается проезд
        logger = logger,
    )

    // прокси КПП, добавляющий логирование событий факта проезда/отказа в доступе и статусов
    val loggedCheckPoint = LoggerProxyCheckpoint(
        checkPoint = checkPoint,
        logger = ConsoleVehicleLogger(), // консольный логгер событий проезда
    )

    loggedCheckPoint.start()
}
