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
            .buildId("WCP-01")
            .buildLogger(logger)
            .buildBarrier(GateBarrier(id = "WGB"))
            .buildVehicleIdentifier(ANPRCameraAdapter(logger))
            .buildAccessHandler(
                WhiteListAccessHandler(
                    whiteList = PostgresWhiteList,
                    numberValidator = SimpleVehicleNumberValidator(),
                    logger = logger,
                )
            )
            .buildBarrierOpenDuration(3_500)
            .buildBarrierDriver(WindowsBarrierDriver(logger))
            .build()
    }

    fun buildPremiumCheckpointFacade(): CheckpointFacade {
        println("[CheckpointFacadeDirector] build from director buildPremiumCheckpointFacade")

        val logger = ConsoleLogger()

        return CheckpointFacadeBuilder()
            .buildId("PREM-CP-01")
            .buildLogger(logger)
            .buildBarrier(BoomBarrier(id = "PREM-BB"))
            .buildVehicleIdentifier(ControllerNFC(logger))
            .buildAccessHandler(
                WhiteListAccessHandler(
                    whiteList = PostgresWhiteList,
                    numberValidator = SimpleVehicleNumberValidator(),
                    logger = logger,
                )
            )
            .buildBarrierOpenDuration(5_000)
            .build()
    }
}
