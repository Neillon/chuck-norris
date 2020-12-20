package com.example.chuck_norris.customview.delegates

import android.view.View
import kotlin.reflect.KProperty

class CustomViewProperty<T>(
    private var customViewValue: T
) {

    operator fun getValue(
        thisRef: View,
        property: KProperty<*>
    ): T {
        return customViewValue
    }

    operator fun setValue(
        thisRef: View,
        property: KProperty<*>,
        value: T
    ) {
        customViewValue = value
        thisRef.invalidate()
    }
}