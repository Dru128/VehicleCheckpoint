package org.dru128.checkpoint

data class CheckpointConfig(
    val openDuration: Long,
    val loggerFilePath: String? = null,
    val vehicleLoggerFilePath: String? = null,
)
