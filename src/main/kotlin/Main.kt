package org.dru128

import org.dru128.access.WhiteListAccessHandler
import org.dru128.barrier.BoomBarrier
import org.dru128.checkpoint.CheckpointFacadeBuilder
import org.dru128.barrier.LinuxBarrierDriver
import org.dru128.log.ConsoleLogger
import org.dru128.storage.PostgresWhiteList
import org.dru128.vehicle.SimpleVehicleNumberValidator

fun main() {
    val logger = ConsoleLogger()

    val simpleCheckPoint = CheckpointFacadeBuilder()
        .setId("SIMPLE-CP-01")
        .setLogger(logger)
        .setBarrier(BoomBarrier(id = "SBB-03"))
//        .setVehicleIdentifier(ControllerNFC(logger))
        .setAccessHandler(
            WhiteListAccessHandler(
                whiteList = PostgresWhiteList,
                numberValidator = SimpleVehicleNumberValidator(),
                logger = logger,
            )
        )
        .setBarrierOpenDuration(7_500)
        .setBarrierDriver(LinuxBarrierDriver(logger))
        .build()
}
