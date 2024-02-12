package com.glebalekseev.lab.core

class MyObservable<T>(initialValue: T) {
    private val observers = mutableListOf<(T) -> Unit>()

    @Volatile
    var value: T = initialValue

    @Synchronized
    fun addObserver(observer: (T) -> Unit) {
        observers.add(observer)
        observer(value)
    }

    @Synchronized
    fun removeObserver(observer: (T) -> Unit) {
        observers.remove(observer)
    }

    @Synchronized
    fun setNewValue(newValue: T) {
        value = newValue
        notifyObservers(value)
    }

    @Synchronized
    private fun notifyObservers(data: T) {
        for (observer in observers) {
            observer(data)
        }
    }
}
