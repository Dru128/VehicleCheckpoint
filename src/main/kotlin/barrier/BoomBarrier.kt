package org.dru128.barrier

open class BoomBarrier(
    override val id: String,
    driver: BarrierDriver,
) : PlatformBarrier(
    id = id,
    barrierKind = "BOOM",
    driver = driver,
)
