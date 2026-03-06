package org.dru128.log

import java.io.File

class FileLogger(private val path: String): Logger {
    private val file = File(path)

    init {
        if (!file.exists()) {
            file.parentFile?.mkdirs()
            file.createNewFile()
        }
    }

    override fun log(tag: String, message: String) {
        val line = "[$tag] $message\n"
        file.appendText(line)
    }

    override fun error(error: String) {
        val line = "[ERROR] $error\n"
        file.appendText(line)
    }
}