package org.dru128.checkpoint

import org.dru128.access.WhiteListAccessHandler
import org.dru128.barrier.*
import org.dru128.identifier.ANPRCameraAdapter
import org.dru128.identifier.ControllerNFC
import org.dru128.log.ConsoleLogger
import org.dru128.storage.PostgresWhiteList
import org.dru128.vehicle.SimpleVehicleNumberValidator

object CheckpointFacadeDirector {
    fun buildWindowsCheckpointFacade(): CheckpointFacade {
        println("[CheckpointFacadeDirector] build from director buildWindowsCheckpointFacade")
        val logger = ConsoleLogger()

        return CheckpointFacadeBuilder()
            .setId("WCP-01")
            .setLogger(logger)
            .setBarrier(GateBarrier(id = "WGB"))
            .setVehicleIdentifier(ANPRCameraAdapter(logger))
            .setAccessHandler(
                WhiteListAccessHandler(
                    whiteList = PostgresWhiteList,
                    numberValidator = SimpleVehicleNumberValidator(),
                    logger = logger,
                )
            )
            .setBarrierOpenDuration(3_500)
            .setBarrierDriver(WindowsBarrierDriver(logger))
            .build()
    }

    fun buildPremiumCheckpointFacade(): CheckpointFacade {
        println("[CheckpointFacadeDirector] build from director buildPremiumCheckpointFacade")

        val logger = ConsoleLogger()

        return CheckpointFacadeBuilder()
            .setId("PREM-CP-01")
            .setLogger(logger)
            .setBarrier(BoomBarrier(id = "PREM-BB"))
            .setVehicleIdentifier(ControllerNFC(logger))
            .setAccessHandler(
                WhiteListAccessHandler(
                    whiteList = PostgresWhiteList,
                    numberValidator = SimpleVehicleNumberValidator(),
                    logger = logger,
                )
            )
            .setBarrierOpenDuration(5_000)
            .build()
    }
}
