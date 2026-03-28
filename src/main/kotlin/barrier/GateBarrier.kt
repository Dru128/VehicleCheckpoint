package org.dru128.barrier

import org.dru128.log.ConsoleLogger

class GateBarrier(
    override val id: String,
    driver: BarrierDriver = LinuxBarrierDriver(ConsoleLogger()),
) : PlatformBarrier(
    id = id,
    barrierKind = "GATE",
    driver = driver,
)
