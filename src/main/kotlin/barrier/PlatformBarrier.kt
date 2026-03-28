package org.dru128.barrier

import org.dru128.log.ConsoleLogger

abstract class PlatformBarrier(
    override val id: String,
    val barrierKind: String,
    var driver: BarrierDriver = LinuxBarrierDriver(ConsoleLogger()),
) : Barrier {
    override val status: Barrier.Status
        get() = driver.status(this)

    override fun open(duration: Long?) {
        driver.open(this, duration)
    }

    override fun close() {
        driver.close(this)
    }
}
