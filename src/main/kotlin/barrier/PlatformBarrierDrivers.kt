package org.dru128.barrier

import org.dru128.log.Logger

abstract class LoggingBarrierDriver(
    private val logger: Logger,
    private val platform: String,
) : BarrierDriver {
    private var status = Barrier.Status.CLOSED

    override fun open(barrier: PlatformBarrier, duration: Long?) {
        when (status(barrier)) {
            Barrier.Status.CLOSED -> {
                status = Barrier.Status.OPEN
                logger.log(
                    "${platform}_${barrier.barrierKind}_OPEN",
                    "${barrier.barrierKind} barrier id=${barrier.id} is open on $platform platform"
                )

                if (duration != null && duration > 0) {
                    logger.log(
                        "${platform}_${barrier.barrierKind}_AUTO_CLOSE",
                        "${barrier.barrierKind} barrier id=${barrier.id} will close in $duration ms on $platform platform"
                    )
                    close(barrier)
                }
            }

            Barrier.Status.OPEN -> {
                logger.log(
                    "${platform}_${barrier.barrierKind}_ALREADY_OPEN",
                    "${barrier.barrierKind} barrier id=${barrier.id} already open on $platform platform"
                )
            }
        }
    }

    override fun close(barrier: PlatformBarrier) {
        when (status(barrier)) {
            Barrier.Status.OPEN -> {
                status = Barrier.Status.CLOSED
                logger.log(
                    "${platform}_${barrier.barrierKind}_CLOSED",
                    "${barrier.barrierKind} barrier id=${barrier.id} closed on $platform platform"
                )
            }

            Barrier.Status.CLOSED -> {
                logger.log(
                    "${platform}_${barrier.barrierKind}_ALREADY_CLOSED",
                    "${barrier.barrierKind} barrier id=${barrier.id} already closed on $platform platform"
                )
            }
        }
    }

    override fun status(barrier: PlatformBarrier): Barrier.Status {
        return status
    }
}

class WindowsBarrierDriver(logger: Logger) : LoggingBarrierDriver(logger, "WINDOWS")

class LinuxBarrierDriver(logger: Logger) : LoggingBarrierDriver(logger, "LINUX")
