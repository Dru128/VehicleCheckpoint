package org.dru128.barrier

class GateBarrier(
    override val id: String,
    driver: BarrierDriver,
) : PlatformBarrier(
    id = id,
    barrierKind = "GATE",
    driver = driver,
)
