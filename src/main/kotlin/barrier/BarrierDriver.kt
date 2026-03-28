package org.dru128.barrier

interface BarrierDriver {
    fun open(barrier: PlatformBarrier, duration: Long? = null)
    fun close(barrier: PlatformBarrier)
    fun status(barrier: PlatformBarrier): Barrier.Status
}

