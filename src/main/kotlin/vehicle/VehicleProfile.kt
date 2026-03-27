package org.dru128.vehicle

data class VehicleProfile(
    val type: Vehicle.Type?,
    val priority: Int,
    val openDuration: Long?,
    val signalEnabled: Boolean,
)

object VehicleProfilePrototypes {
    val car = VehicleProfile(
        type = Vehicle.Type.CAR,
        priority = 1,
        openDuration = 2_000,
        signalEnabled = false,
    )
        get() {
            println("[VehicleProfilePrototypes] return prototype: $field ")
            return field
        }

    val taxi = VehicleProfile(
        type = Vehicle.Type.TAXI,
        priority = 2,
        openDuration = 2_500,
        signalEnabled = false,
    )
        get() {
            println("[VehicleProfilePrototypes] return prototype: $field ")
            return field
        }

    val truck = VehicleProfile(
        type = Vehicle.Type.TRUCK,
        priority = 3,
        openDuration = 4_000,
        signalEnabled = true,
    )
        get() {
            println("[VehicleProfilePrototypes] return prototype: $field ")
            return field
        }

    val motorbike = VehicleProfile(
        type = Vehicle.Type.MOTORBIKE,
        priority = 1,
        openDuration = 1_500,
        signalEnabled = false,
    )
        get() {
            println("[VehicleProfilePrototypes] return prototype: $field ")
            return field
        }

    val emergency = VehicleProfile(
        type = Vehicle.Type.EMERGENCY,
        priority = 10,
        openDuration = 1_000,
        signalEnabled = true,
    )
        get() {
            println("[VehicleProfilePrototypes] return prototype: $field ")
            return field
        }

    val unknown = VehicleProfile(
        type = null,
        priority = 0,
        openDuration = 2_000,
        signalEnabled = false,
    )
        get() {
        println("[VehicleProfilePrototypes] return prototype: $field ")
        return field
    }

}

abstract class VehicleProfileCreator(
    private val type: Vehicle.Type?,
) {
    fun create(): VehicleProfile {
        return createProfile(type)
    }

    protected abstract fun createProfile(type: Vehicle.Type?): VehicleProfile
}

open class CarProfileCreator: VehicleProfileCreator(Vehicle.Type.CAR) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.car.copy(type = type)
        println("[CarProfileCreator] factory method create: $profile")
        return profile
    }
}

open class TaxiProfileCreator : VehicleProfileCreator(Vehicle.Type.TAXI) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.taxi.copy(type = type)
        println("[TaxiProfileCreator] factory method create: $profile")
        return profile
    }
}

open class TruckProfileCreator : VehicleProfileCreator(Vehicle.Type.TRUCK) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.truck.copy(type = type)
        println("[TruckProfileCreator] factory method create: $profile")
        return profile
    }
}

open class MotorbikeProfileCreator : VehicleProfileCreator(Vehicle.Type.MOTORBIKE) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.motorbike.copy(type = type)
        println("[MotorbikeProfileCreator] factory method create: $profile")
        return profile
    }
}

open class EmergencyProfileCreator : VehicleProfileCreator(Vehicle.Type.EMERGENCY) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.emergency.copy(type = type)
        println("[EmergencyProfileCreator] factory method create: $profile")
        return profile
    }
}

open class UnknownProfileCreator : VehicleProfileCreator(null) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.unknown.copy(type = type)
        println("[UnknownProfileCreator] factory method create: $profile")
        return profile
    }
}

object VehicleProfileFactory {
    fun create(type: Vehicle.Type?): VehicleProfile {
        return when (type) {
            Vehicle.Type.CAR -> CarProfileCreator().create()
            Vehicle.Type.TAXI -> TaxiProfileCreator().create()
            Vehicle.Type.TRUCK -> TruckProfileCreator().create()
            Vehicle.Type.MOTORBIKE -> MotorbikeProfileCreator().create()
            Vehicle.Type.EMERGENCY -> EmergencyProfileCreator().create()
            null -> UnknownProfileCreator().create()
        }
    }
}
