package org.dru128.mock

import org.dru128.vehicle.Vehicle

class IncomingVehicles : Iterable<Vehicle> {
    companion object {
        val vehicleRows = arrayOf(
            "A120CD" to Vehicle.Type.CAR,
            "C312HE" to Vehicle.Type.CAR,
            "S000TE" to Vehicle.Type.CAR,
            "ttt" to Vehicle.Type.CAR, // невалидный номер
            "M120CD" to Vehicle.Type.TAXI,
            "N120CD" to Vehicle.Type.TRUCK,
            "E120CD" to Vehicle.Type.MOTORBIKE,
            "P120CD" to Vehicle.Type.EMERGENCY, // скорая с нормальным номером
            "345876" to Vehicle.Type.EMERGENCY, // скорая с невалидным номером
            "C999AB" to null, // нераспознанный тип транспорта
        )

        fun getRandomVehicle(): Vehicle {
            val vehicleRow = vehicleRows.random()
            return Vehicle(
                id = vehicleRow.first,
                type = vehicleRow.second,
            )
        }
    }

    override fun iterator(): Iterator<Vehicle> {
        return IncomingVehicleIterator(vehicleRows)
    }
}
