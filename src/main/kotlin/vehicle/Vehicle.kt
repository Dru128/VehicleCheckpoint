package org.dru128.vehicle

data class Vehicle(
    val number: LicensePlate,
    val profile: VehicleProfile,
) {
    constructor(id: String, type: Type? = null) : this(
        number = LicensePlate(id),
        profile = VehicleProfileFactory.get(type),
    )

    val id: String
        get() = number.number

    val normalizedId: String
        get() = number.normalized()

    val type: Type?
        get() = profile.type

    fun hasNumber(): Boolean = number.isPresent()

    fun isValidNumber(): Boolean = number.isValid()

    fun isEmergency(): Boolean = type == Type.EMERGENCY

    fun openDurationOrDefault(defaultDuration: Long?): Long? {
        return profile.openDuration ?: defaultDuration
    }

    enum class Type {
        CAR,
        TAXI,
        TRUCK,
        MOTORBIKE,
        EMERGENCY,
    }
}
