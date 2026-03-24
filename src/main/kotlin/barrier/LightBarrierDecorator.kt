package org.dru128.barrier

import org.dru128.log.Logger

class LightBarrierDecorator(
    val barrier: Barrier,
    val logger: Logger,
): Barrier {
    override val id: String
        get() = barrier.id

    override val status: Barrier.Status
        get() = barrier.status

    override fun open(duration: Long?) {
        logger.log("LightBarrierDecorator", "Barrier id=$id light ON")
        barrier.open(duration)
        logger.log("LightBarrierDecorator", "Barrier id=$id light OFF")
    }

    override fun close() {
        logger.log("LightBarrierDecorator", "Barrier id=$id light OFF")
        barrier.close()
    }
}
