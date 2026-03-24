package org.dru128.barrier

import org.dru128.log.Logger

// Подъемный шлагбаум
open class BoomBarrier(
    private val logger: Logger,
    override val id: String,
): Barrier {
    override var status: Barrier.Status = Barrier.Status.CLOSED
        protected set

    override fun open(duration: Long?) {
        when (status) {
            Barrier.Status.CLOSED -> {
                status = Barrier.Status.OPEN
                logger.log("BARRIER_OPEN", "Barrier id=$id is open")

                if (duration != null && duration > 0) {
                    logger.log("BARRIER_AUTO_CLOSE", "Barrier id=$id will close in $duration ms")
                    close()
                }
            }

            Barrier.Status.OPEN -> {
                logger.log("BARRIER_ALREADY_OPEN", "Barrier id=$id already open")
            }
        }
    }

    override fun close() {
        when (status) {
            Barrier.Status.OPEN -> {
                status = Barrier.Status.CLOSED
                logger.log("BARRIER_CLOSED", "Barrier id=$id closed")
            }

            Barrier.Status.CLOSED -> {
                logger.log("BARRIER_ALREADY_CLOSED", "Barrier id=$id already closed")
            }
        }
    }
}
