package org.dru128.barrier

import org.dru128.log.ConsoleLogger

open class BoomBarrier(
    override val id: String,
    driver: BarrierDriver = LinuxBarrierDriver(ConsoleLogger()),
) : PlatformBarrier(
    id = id,
    barrierKind = "BOOM",
    driver = driver,
)
