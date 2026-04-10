package org.dru128.log

import org.dru128.vehicle.Vehicle
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConsoleLogger: Logger {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun log(tag: String, message: String) {
        val timestamp = LocalDateTime.now().format(dateTimeFormatter)
        println("[$timestamp] [$tag] $message")
    }

    override fun error(error: String) {
        println("[ERROR] $error")
    }

    override fun update(value: Vehicle?) {
        if (value != null) {
            println("[LoggerObserver] vehicle=${value.profile}")
        }
    }
}