package com.example.chuck_norris.abstractions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class StateViewModel<T, S, U>: ViewModel() {

    protected val _viewState = MutableLiveData<T>()
    val viewState: LiveData<T>
        get() = _viewState

    protected val _viewEffect = MutableLiveData<U>()
    val viewEffect: LiveData<U>
        get() = _viewEffect

    abstract fun processEvent(event: S)
}