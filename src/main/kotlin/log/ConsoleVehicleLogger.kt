package org.dru128.log

import org.dru128.AccessResult
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConsoleVehicleLogger: VehicleLogger {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun log(event: AccessResult, message: String) {
        val timestamp = LocalDateTime.now().format(dateTimeFormatter)
        println("[$timestamp] [$event] $message")
    }
}
