package org.dru128.sdk

interface CameraSdk {
    fun listenFrames(callback: (VehiclePhoto) -> Unit)
}
