package org.dru128.barrier

interface BarrierDriver {
    fun open(id: String, barrierKind: String, duration: Long? = null)
    fun close(id: String, barrierKind: String)
    fun status(id: String): Barrier.Status
}
