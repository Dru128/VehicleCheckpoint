package org.dru128.barrier

import org.dru128.log.Logger

// Подъемный шлагбаум
open class BoomBarrier(
    private val logger: Logger,
): Barrier {
    override var status: Barrier.Status = Barrier.Status.CLOSED
        protected set

    override fun open(duration: Long?) {
        when (status) {
            Barrier.Status.CLOSED -> {
                status = Barrier.Status.OPENING
                logger.log("BARRIER_OPENING", "Barrier is opening")

                // имитация процесса открытия
                Thread.sleep(1000)

                status = Barrier.Status.OPEN
                logger.log("BARRIER_OPEN", "Barrier is open")

                if (duration != null && duration > 0) {
                    logger.log("BARRIER_AUTO_CLOSE", "Barrier will close in $duration ms")

                    // закрытие после истечения времени
                    Thread.sleep(duration)
                    close()
                }
            }

            Barrier.Status.OPEN -> {
                logger.log("BARRIER_ALREADY_OPEN", "Barrier already open")
            }

            Barrier.Status.OPENING,
            Barrier.Status.CLOSING -> {
                logger.log("BARRIER_BUSY", "Barrier is moving, cannot open")
            }
        }
    }

    override fun close() {
        when (status) {
            Barrier.Status.OPEN -> {
                status = Barrier.Status.CLOSING
                logger.log("BARRIER_CLOSING", "Barrier is closing")

                // Имитация процесса закрытия
                Thread.sleep(1000)

                status = Barrier.Status.CLOSED
                logger.log("BARRIER_CLOSED", "Barrier closed")
            }

            Barrier.Status.CLOSED -> {
                logger.log("BARRIER_ALREADY_CLOSED", "Barrier already closed")
            }

            Barrier.Status.OPENING,
            Barrier.Status.CLOSING -> {
                logger.log("BARRIER_BUSY", "Barrier is moving, cannot close")
            }
        }
    }
}
