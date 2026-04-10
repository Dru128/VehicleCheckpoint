package org.dru128

import org.dru128.access.WhiteListAccessHandler
import org.dru128.barrier.BoomBarrier
import org.dru128.checkpoint.CheckpointFacadeBuilder
import org.dru128.barrier.LinuxBarrierDriver
import org.dru128.identifier.ControllerNFC
import org.dru128.log.ConsoleLogger
import org.dru128.storage.PostgresWhiteList
import org.dru128.vehicle.SimpleVehicleNumberValidator

fun main() {
    val logger = ConsoleLogger()

    val simpleCheckPoint = CheckpointFacadeBuilder()
        .buildId("SIMPLE-CP-01")
        .buildLogger(logger)
        .buildBarrier(BoomBarrier(id = "SBB-03"))
        .buildVehicleIdentifier(ControllerNFC(logger))
        .buildAccessHandler(
            WhiteListAccessHandler(
                whiteList = PostgresWhiteList,
                numberValidator = SimpleVehicleNumberValidator(),
                logger = logger,
            )
        )
        .buildBarrierOpenDuration(7_500)
        .buildBarrierDriver(LinuxBarrierDriver(logger))
        .build()

    simpleCheckPoint.start()
}
