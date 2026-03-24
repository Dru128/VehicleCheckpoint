package org.dru128.barrier

import org.dru128.log.Logger

class SoundBarrierDecorator(
    val barrier: Barrier,
    val logger: Logger,
): Barrier {
    override val id: String
        get() = barrier.id

    override val status: Barrier.Status
        get() = barrier.status

    override fun open(duration: Long?) {
        logger.log("SoundBarrierDecorator", "Barrier id=$id sound ON")
        barrier.open()
        logger.log("SoundBarrierDecorator", "Barrier id=$id sound OFF")
    }

    override fun close() {
        logger.log("SoundBarrierDecorator", "Barrier id=$id sound OFF")
        barrier.close()
    }
}
