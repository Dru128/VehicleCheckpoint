package org.dru128.sdk

import org.dru128.mock.IncomingVehicles

class APNRCamera: CameraSdk {
    override fun listenFrames(callback: (VehiclePhoto) -> Unit) {
        val vehicleIterator = IncomingVehicles.iterator()

        while (vehicleIterator.hasNext()) {
            val vehicle = vehicleIterator.next()
            val frameId = "frame${vehicle.hashCode()}"

            callback(
                VehiclePhoto(
                    frameId = "frame-$frameId",
                    uri = "mock://camera/$frameId.jpg",
                )
            )
        }
    }
}

object APNRCameraPool {
    private val poolSize = 2
    private val available = ArrayDeque<APNRCamera>()
    private var createdCount = 0

    fun pop(): APNRCamera {
        println("[APNRCameraPool] pop: used = ${usedCount()}/$poolSize")
        val cachedCamera = available.removeFirstOrNull()
        if (cachedCamera != null) {
            println("[APNRCameraPool] pop: available camera return, pool = ${usedCount()}/$poolSize")
            return cachedCamera
        }

        if (createdCount < poolSize) {
            createdCount++
            println("[APNRCameraPool] pop: new camera created return, pool = ${usedCount()}/$poolSize")
            return APNRCamera()
        }

        println("[APNRCameraPool] pop: no free pooled camers! pool = ${usedCount()}/$poolSize")
        return APNRCamera() // wait
    }

    fun push(camera: APNRCamera) {
        if (available.size < poolSize) {
            available.addLast(camera)
        }
        println("[APNRCameraPool] push: camera returned, used = ${usedCount()}/$poolSize")
    }

    private fun usedCount(): Int {
        return createdCount - available.size
    }
}
