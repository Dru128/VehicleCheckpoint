package org.dru128.vehicle

data class Vehicle(
    var id: String,
    var type: Type? = null
) {
    enum class Type {
        CAR,
        TAXI,
        TRUCK,
        MOTORBIKE,
        EXTRA,
    }
}
