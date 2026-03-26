package org.dru128.barrier

abstract class PlatformBarrier(
    override val id: String,
    private val barrierKind: String,
    private val driver: BarrierDriver,
) : Barrier {
    override val status: Barrier.Status
        get() = driver.status(id)

    override fun open(duration: Long?) {
        driver.open(id, barrierKind, duration)
    }

    override fun close() {
        driver.close(id, barrierKind)
    }
}
