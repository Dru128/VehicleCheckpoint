package org.dru128.sdk

interface ExternalCameraSdk {
    fun listenFrames(callback: (VehiclePhoto) -> Unit)
}
