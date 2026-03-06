package org.dru128.log

import org.dru128.AccessResult
import java.io.File

class FileVehicleLogger(private val path: String): VehicleLogger {
    private val file = File(path)

    init {
        if (!file.exists()) {
            file.parentFile?.mkdirs()
            file.createNewFile()
        }
    }

    override fun log(event: AccessResult, message: String) {
        val line = "[$event] $message\n"
        file.appendText(line)
    }
}