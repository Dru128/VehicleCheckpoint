package org.dru128.barrier

import org.dru128.log.Logger

interface BarrierCommand {
    fun execute()
}

class OpenBarrierCommand(
    private val barrier: Barrier,
    private val logger: Logger,
): BarrierCommand {
    override fun execute() {
        logger.log("OpenBarrierCommand", "barrierId=${barrier.id}")
        barrier.open()
    }
}

class CloseBarrierCommand(
    private val barrier: Barrier,
    private val logger: Logger,
): BarrierCommand {
    override fun execute() {
        logger.log("CloseBarrierCommand", "barrierId=${barrier.id}")
        barrier.close()
    }
}

class OpenBarrierWithTimeoutCommand(
    private val barrier: Barrier,
    private val timeout: Long,
    private val logger: Logger,
): BarrierCommand {
    override fun execute() {
        logger.log(
            "OpenBarrierWithTimeoutCommand",
            "barrierId=${barrier.id}, timeout=$timeout"
        )
        barrier.open(timeout)
    }
}