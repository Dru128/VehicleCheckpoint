package org.dru128.log

interface Logger {
    fun log(tag: String, message: String)
    fun error(error: String)
}