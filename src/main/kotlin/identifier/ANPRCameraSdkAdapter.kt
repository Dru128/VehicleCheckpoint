package org.dru128.identifier

import org.dru128.sdk.ExternalCameraSdk
import org.dru128.vehicle.Vehicle

class AnprCameraSdkAdapter(
    private val sdk: ExternalCameraSdk,
) : VehicleIdentfier {
    override fun onDetectVehicle(callback: (Vehicle) -> Unit) {
        sdk.listenFrames { photo ->
            val plateNumber = sdk.extractPlateNumber(photo) ?: return@listenFrames

            callback.invoke(
                Vehicle(
                    id = plateNumber,
                    type = photo.vehicleTypeHint,
                )
            )
        }
    }
}
