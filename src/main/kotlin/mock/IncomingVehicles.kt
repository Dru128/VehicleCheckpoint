package org.dru128.mock

import org.dru128.vehicle.Vehicle

object IncomingVehicles : Iterable<Vehicle> {
    val vehicles = listOf(
        Vehicle(id = "A120CD", type = Vehicle.Type.CAR),
        Vehicle(id = "C312HE", type = Vehicle.Type.CAR),
        Vehicle(id = "S000TE", type = Vehicle.Type.CAR),

        Vehicle(id = "ttt", type = Vehicle.Type.CAR), // невалидный номер

        Vehicle(id = "M120CD", type = Vehicle.Type.TAXI),
        Vehicle(id = "N120CD", type = Vehicle.Type.TRUCK),
        Vehicle(id = "E120CD", type = Vehicle.Type.MOTORBIKE),
        Vehicle(id = "P120CD", type = Vehicle.Type.EMERGENCY), // скорая с нормальным номером
        Vehicle(id = "345876", type = Vehicle.Type.EMERGENCY), // скорая с невалидным номером


        Vehicle(id = "C999AB", type = null), // Нераспознанный тип транспорта

    )

    fun getRandomVehicle() = vehicles.random()

    override fun iterator(): Iterator<Vehicle> {
        return IncomingVehicleIterator(vehicles)
    }
}
