package org.dru128.vehicle

class LicensePlate(
    val number: String,
) {
    fun normalized(): String = number.trim().uppercase()

    fun isPresent(): Boolean = normalized().isNotEmpty()

    fun isValid(): Boolean = Regex("^[A-Z]\\d{3}[A-Z]{2}$").matches(normalized())
}
