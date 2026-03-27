package org.dru128

import org.dru128.checkpoint.CheckpointConfig
import org.dru128.checkpoint.CheckpointFacade
import org.dru128.checkpoint.LinuxCheckpointFactory
import org.dru128.checkpoint.WindowsCheckpointFactory

fun main() {
    val config = CheckpointConfig(
        openDuration = 2_000,
        loggerFilePath = null,
        vehicleLoggerFilePath = null,
    )

    val windowsCheckPoint: CheckpointFacade = WindowsCheckpointFactory.create(config)
    val linuxCheckPoint: CheckpointFacade = LinuxCheckpointFactory.create(config)

    Thread {
        windowsCheckPoint.start()
    }.start()

    Thread {
        linuxCheckPoint.start()
    }.start()
}
