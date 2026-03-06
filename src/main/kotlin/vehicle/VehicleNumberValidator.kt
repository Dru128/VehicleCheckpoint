package org.dru128.vehicle

interface VehicleNumberValidator {
    fun validate(number: String): Boolean
}