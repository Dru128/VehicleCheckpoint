package org.dru128.barrier

interface Barrier {
    val status: Status

    fun open(duration: Long? = null)
    fun close()

    enum class Status {
        OPENING,
        OPEN,
        CLOSING,
        CLOSED
    }
}