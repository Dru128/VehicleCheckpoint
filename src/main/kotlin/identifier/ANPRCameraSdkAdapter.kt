package org.dru128.identifier

import org.dru128.sdk.ExternalCameraSdk
import org.dru128.vehicle.Vehicle

// Извлечение из фото номера ТС и его типа
class ANPRCameraSdkAdapter(
    private val cameraSdk: ExternalCameraSdk,
) : VehicleIdentfier {
    override fun onDetectVehicle(callback: (Vehicle) -> Unit) {
        cameraSdk.listenFrames { photo ->
            // Имитация компьютерного зрения, которое анализирует фото
            val plateNumber = cameraSdk.extractNumber(photo) ?: return@listenFrames

            callback.invoke(
                Vehicle(
                    id = plateNumber,
                    type = cameraSdk.resolveVehicleType(photo),
                )
            )
        }
    }
}
