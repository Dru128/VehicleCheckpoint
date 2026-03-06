package org.dru128.log

class ConsoleLogger: Logger {
    override fun log(tag: String, message: String) {
        println("[$tag] $message")
    }

    override fun error(error: String) {
        println("[ERROR] $error")
    }
}