package org.dru128.checkpoint

import org.dru128.AccessResult
import org.dru128.barrier.Barrier
import org.dru128.identifier.VehicleIdentfier
import org.dru128.log.VehicleLogger

class LoggerProxyCheckpoint(
    private val checkPoint: SimpleCheckPoint,
    private val logger: VehicleLogger,
): Checkpoint {
    override val isActive: Boolean
        get() = checkPoint.isActive

    override val barrier
        get() = checkPoint.barrier

    override val vehicleIdentfier
        get() = checkPoint.vehicleIdentfier

    override fun setVehicleIdetifier(vehicleIdentfier: VehicleIdentfier) {
        checkPoint.setVehicleIdetifier(vehicleIdentfier)
    }

    override fun setBarrier(barrier: Barrier) {
        checkPoint.setBarrier(barrier)
    }

    override fun start() {
        checkPoint.start()
        accessHandler()
    }

    override fun stop() {
        checkPoint.stop()
    }

    override fun accessHandler() {
        checkPoint.vehicleIdentfier.onDetectVehicle { vehicle ->
            if (checkPoint.isActive) {
                val result = checkPoint.accessHandler.handle(vehicle)
                logger.log(result, "checkPointId=${checkPoint.id}, vehicle=$vehicle")

                if (result == AccessResult.ACCESS_APPROVED) {
                    checkPoint.barrier.open(duration = checkPoint.openDuration)
                }
            }
        }
    }
}
