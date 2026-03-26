package org.dru128.vehicle

class SimpleVehicleNumberValidator : VehicleNumberValidator {
    override fun validate(number: String): Boolean {
        return LicensePlate(number).isValid()
    }
}
