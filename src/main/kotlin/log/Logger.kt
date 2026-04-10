package org.dru128.log

import org.dru128.observer.Observer
import org.dru128.vehicle.Vehicle

interface Logger: Observer<Vehicle?> {
    fun log(tag: String, message: String)
    fun error(error: String)
}