package org.dru128.sdk

interface ExternalCameraSdk {
    fun listenFrames(onFrameCaptured: (VehiclePhoto) -> Unit)
    fun extractNumber(photo: VehiclePhoto): String?
}
