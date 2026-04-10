package org.dru128.observer

interface Observer<T> {
    fun update(value: T)
}