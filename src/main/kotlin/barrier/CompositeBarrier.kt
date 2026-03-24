package org.dru128.barrier

import org.dru128.log.Logger

class CompositeBarrier(
    private val barriers: List<Barrier>,
    val logger: Logger,
    override val id: String,
) : Barrier {

    override val status: Barrier.Status
        get() = when {
            barriers.isNotEmpty() && barriers.all { it.status == Barrier.Status.OPEN } -> Barrier.Status.OPEN
            else -> Barrier.Status.CLOSED
        }

    override fun open(duration: Long?) {
        logger.log("BARRIER_GROUP_OPEN", "GROUP id=$id is open")
        barriers.forEach {
            it.open(duration)
        }
    }

    override fun close() {
        logger.log("BARRIER_GROUP_CLOSE", "GROUP id=$id is closed")
        barriers.forEach {
            it.close()
        }
    }
}
