package org.dru128.barrier

import org.dru128.log.Logger

// Ворота
class GateBarrier(
    private val logger: Logger,
    override val id: String,
): Barrier {
    override var status: Barrier.Status = Barrier.Status.CLOSED
        protected set

    override fun open(duration: Long?) {
        when (status) {
            Barrier.Status.CLOSED -> {
                status = Barrier.Status.OPEN
                logger.log("GATE_OPEN", "Gate id=$id is open")

                if (duration != null && duration > 0) {
                    logger.log("GATE_AUTO_CLOSE", "Gate id=$id will close in $duration ms")
                    close()
                }
            }

            Barrier.Status.OPEN -> {
                logger.log("GATE_ALREADY_OPEN", "Gate id=$id already open")
            }
        }
    }

    override fun close() {
        when (status) {
            Barrier.Status.OPEN -> {
                status = Barrier.Status.CLOSED
                logger.log("GATE_CLOSED", "Gate id=$id closed")
            }

            Barrier.Status.CLOSED -> {
                logger.log("GATE_ALREADY_CLOSED", "Gate id=$id already closed")
            }
        }
    }
}
