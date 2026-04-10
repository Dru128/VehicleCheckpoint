package org.dru128.observer

class Subject<T> (
    value: T
){
    var value = value
        set(value) {
            field = value
            notify0()
        }

    private val observers = mutableListOf<Observer<T>>()

    fun attach(observer: Observer<T>) {
        observers.add(observer)
    }

    fun detach(observer: Observer<T>) {
        observers.remove(observer)
    }

    fun notify0() {
        observers.forEach {
            it.update(value)
        }
    }
}
