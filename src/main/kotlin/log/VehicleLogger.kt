package org.dru128.log

import org.dru128.AccessResult

interface VehicleLogger {
    fun log(event: AccessResult, message: String)
}
