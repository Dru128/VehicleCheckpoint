package org.dru128.barrier

class CompositeBarrier(
    private val barriers: List<Barrier>,
) : Barrier {
    override val status: Barrier.Status
        get() = when {
            barriers.any { it.status == Barrier.Status.OPENING } -> Barrier.Status.OPENING
            barriers.any { it.status == Barrier.Status.CLOSING } -> Barrier.Status.CLOSING
            barriers.isNotEmpty() && barriers.all { it.status == Barrier.Status.OPEN } -> Barrier.Status.OPEN
            else -> Barrier.Status.CLOSED
        }

    override fun open(duration: Long?) {
        barriers.forEach {
            it.open(duration)
        }
    }

    override fun close() {
        barriers.forEach {
            it.close()
        }
    }
}
