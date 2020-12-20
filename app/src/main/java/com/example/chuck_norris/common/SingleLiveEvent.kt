package com.example.chuck_norris.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * This class should be used when:
 * - We want to call single shot events on the screen
 * - When we don`t need to listen the events eternally
 */
class SingleLiveEvent<T>: MutableLiveData<T>() {

    private val pending =  AtomicBoolean(false)

    /**
     * Override the default observer to understand if the value is pending or not
     */
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)

        if (hasActiveObservers())
            Timber.w("There is active observers. Only one will notify changes")

        super.observe(owner, { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    /**
     * Override the setValue to put the event pending
     */
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }

    /**
     * To call an Event
     * Set the pending as true and value must is going to be null
     */
    fun call() {
        value = null
    }

}