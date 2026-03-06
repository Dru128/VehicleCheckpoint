package org.dru128.barrier

import org.dru128.log.Logger

// Ворота
class GateBarrier(
    private val logger: Logger,
): Barrier {
    override var status: Barrier.Status = Barrier.Status.CLOSED
        protected set

    override fun open(duration: Long?) {
        when (status) {
            Barrier.Status.CLOSED -> {
                status = Barrier.Status.OPENING
                logger.log("GATE_OPENING", "Gate is opening")

                Thread.sleep(1000)

                status = Barrier.Status.OPEN
                logger.log("GATE_OPEN", "Gate is open")

                if (duration != null && duration > 0) {
                    logger.log("GATE_AUTO_CLOSE", "Gate will close in $duration ms")

                    Thread.sleep(duration)
                    close()
                }
            }

            Barrier.Status.OPEN -> {
                logger.log("GATE_ALREADY_OPEN", "Gate already open")
            }

            Barrier.Status.OPENING,
            Barrier.Status.CLOSING -> {
                logger.log("GATE_BUSY", "Gate is moving, cannot open")
            }
        }
    }

    override fun close() {
        when (status) {
            Barrier.Status.OPEN -> {
                status = Barrier.Status.CLOSING
                logger.log("GATE_CLOSING", "Gate is closing")

                Thread.sleep(1000)

                status = Barrier.Status.CLOSED
                logger.log("GATE_CLOSED", "Gate closed")
            }

            Barrier.Status.CLOSED -> {
                logger.log("GATE_ALREADY_CLOSED", "Gate already closed")
            }

            Barrier.Status.OPENING,
            Barrier.Status.CLOSING -> {
                logger.log("GATE_BUSY", "Gate is moving, cannot close")
            }
        }
    }
}
