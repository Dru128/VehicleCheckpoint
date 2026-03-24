package org.dru128.log

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FileLogger(private val path: String): Logger {
    private val file = File(path)
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    init {
        if (!file.exists()) {
            file.parentFile?.mkdirs()
            file.createNewFile()
        }
    }

    override fun log(tag: String, message: String) {
        val timestamp = LocalDateTime.now().format(dateTimeFormatter)
        val line = "[$timestamp] [$tag] $message\n"
        file.appendText(line)
    }

    override fun error(error: String) {
        val timestamp = LocalDateTime.now().format(dateTimeFormatter)
        val line = "[$timestamp] [ERROR] $error\n"
        file.appendText(line)
    }
}