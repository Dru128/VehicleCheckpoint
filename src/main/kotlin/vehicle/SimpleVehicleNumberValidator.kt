package org.dru128.vehicle

// Формат 2 заглавных латинских символа, 3 цифры и 1 заглавный латинский символ
class SimpleVehicleNumberValidator: VehicleNumberValidator {
    private val numberPattern = Regex("^[A-Z]\\d{3}[A-Z]{2}$")

    override fun validate(number: String): Boolean {
        return numberPattern.matches(number)
    }
}
