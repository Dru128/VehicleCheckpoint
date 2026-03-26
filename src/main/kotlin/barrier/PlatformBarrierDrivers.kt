package org.dru128.barrier

import org.dru128.log.Logger

abstract class LoggingBarrierDriver(
    private val logger: Logger,
    private val platform: String,
) : BarrierDriver {
    private var status = Barrier.Status.CLOSED

    override fun open(id: String, barrierKind: String, duration: Long?) {
        when (status(id)) {
            Barrier.Status.CLOSED -> {
                status = Barrier.Status.OPEN
                logger.log("${platform}_${barrierKind}_OPEN", "$barrierKind barrier id=$id is open on $platform platform")

                if (duration != null && duration > 0) {
                    logger.log("${platform}_${barrierKind}_AUTO_CLOSE", "$barrierKind barrier id=$id will close in $duration ms on $platform platform")
                    close(id, barrierKind)
                }
            }

            Barrier.Status.OPEN -> {
                logger.log("${platform}_${barrierKind}_ALREADY_OPEN", "$barrierKind barrier id=$id already open on $platform platform")
            }
        }
    }

    override fun close(id: String, barrierKind: String) {
        when (status(id)) {
            Barrier.Status.OPEN -> {
                status = Barrier.Status.CLOSED
                logger.log("${platform}_${barrierKind}_CLOSED", "$barrierKind barrier id=$id closed on $platform platform")
            }

            Barrier.Status.CLOSED -> {
                logger.log("${platform}_${barrierKind}_ALREADY_CLOSED", "$barrierKind barrier id=$id already closed on $platform platform")
            }
        }
    }

    override fun status(id: String): Barrier.Status {
        return status
    }
}

class WindowsBarrierDriver(logger: Logger) : LoggingBarrierDriver(logger, "WINDOWS")

class LinuxBarrierDriver(logger: Logger) : LoggingBarrierDriver(logger, "LINUX")