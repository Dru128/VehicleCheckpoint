package org.dru128.barrier

interface Barrier {
    val id: String
    val status: Status

    fun open(duration: Long? = null)
    fun close()

    enum class Status {
        OPEN,
        CLOSED
    }
}