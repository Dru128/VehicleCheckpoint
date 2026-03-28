package org.dru128.vehicle

data class VehicleProfile(
    val type: Vehicle.Type?,
    val priority: Int,
    val openDuration: Long?,
    val signalEnabled: Boolean,
) {
    fun clone(): VehicleProfile {
        return VehicleProfile(
            type = this.type,
            priority = this.priority,
            openDuration = this.openDuration,
            signalEnabled = this.signalEnabled,
        )
    }
}

object VehicleProfilePrototypes {
    val car = VehicleProfile(
        type = Vehicle.Type.CAR,
        priority = 1,
        openDuration = 2_000,
        signalEnabled = false,
    )
        get() {
            println("[VehicleProfilePrototypes] clone prototype: $field ")
            return field.clone()
        }

    val taxi = VehicleProfile(
        type = Vehicle.Type.TAXI,
        priority = 2,
        openDuration = 2_500,
        signalEnabled = false,
    )
        get() {
            println("[VehicleProfilePrototypes] clone prototype: $field ")
            return field.clone()
        }

    val truck = VehicleProfile(
        type = Vehicle.Type.TRUCK,
        priority = 3,
        openDuration = 4_000,
        signalEnabled = true,
    )
        get() {
            println("[VehicleProfilePrototypes] clone prototype: $field ")
            return field.clone()
        }

    val motorbike = VehicleProfile(
        type = Vehicle.Type.MOTORBIKE,
        priority = 1,
        openDuration = 1_500,
        signalEnabled = false,
    )
        get() {
            println("[VehicleProfilePrototypes] clone prototype: $field ")
            return field.clone()
        }

    val emergency = VehicleProfile(
        type = Vehicle.Type.EMERGENCY,
        priority = 10,
        openDuration = 1_000,
        signalEnabled = true,
    )
        get() {
            println("[VehicleProfilePrototypes] clone prototype: $field ")
            return field.clone()
        }

    val unknown = VehicleProfile(
        type = null,
        priority = 0,
        openDuration = 2_000,
        signalEnabled = false,
    )
        get() {
        println("[VehicleProfilePrototypes] clone prototype: $field ")
        return field.clone()
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

class CarProfileCreator: VehicleProfileCreator(Vehicle.Type.CAR) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.car
        println("[CarProfileCreator] factory method create: $profile")
        return profile
    }
}

class TaxiProfileCreator : VehicleProfileCreator(Vehicle.Type.TAXI) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.taxi
        println("[TaxiProfileCreator] factory method create: $profile")
        return profile
    }
}

class TruckProfileCreator : VehicleProfileCreator(Vehicle.Type.TRUCK) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.truck
        println("[TruckProfileCreator] factory method create: $profile")
        return profile
    }
}

class MotorbikeProfileCreator : VehicleProfileCreator(Vehicle.Type.MOTORBIKE) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.motorbike
        println("[MotorbikeProfileCreator] factory method create: $profile")
        return profile
    }
}

class EmergencyProfileCreator : VehicleProfileCreator(Vehicle.Type.EMERGENCY) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.emergency
        println("[EmergencyProfileCreator] factory method create: $profile")
        return profile
    }
}

class UnknownProfileCreator : VehicleProfileCreator(null) {
    override fun createProfile(type: Vehicle.Type?): VehicleProfile {
        val profile = VehicleProfilePrototypes.unknown
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
