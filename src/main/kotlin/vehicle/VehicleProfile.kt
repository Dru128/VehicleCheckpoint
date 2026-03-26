package org.dru128.vehicle

data class VehicleProfile(
    val type: Vehicle.Type?,
    val priority: Int,
    val openDuration: Long?,
    val signalEnabled: Boolean,
)

object VehicleProfileFactory {
    private val profiles = mutableMapOf<Vehicle.Type?, VehicleProfile>()

    fun get(type: Vehicle.Type?): VehicleProfile {
        return profiles.getOrPut(type) {
            when (type) {
                Vehicle.Type.CAR -> VehicleProfile(type, priority = 1, openDuration = 2_000, signalEnabled = false)
                Vehicle.Type.TAXI -> VehicleProfile(type, priority = 2, openDuration = 2_500, signalEnabled = false)
                Vehicle.Type.TRUCK -> VehicleProfile(type, priority = 3, openDuration = 4_000, signalEnabled = true)
                Vehicle.Type.MOTORBIKE -> VehicleProfile(type, priority = 1, openDuration = 1_500, signalEnabled = false)
                Vehicle.Type.EMERGENCY -> VehicleProfile(type, priority = 10, openDuration = 1_000, signalEnabled = true)
                null -> VehicleProfile(type, priority = 0, openDuration = 2_000, signalEnabled = false)
            }
        }
    }
}
