package com.rubylight.android.statistics

class EventProperties: BaseProperties<Any?>() {

    fun set(property: String, value: Any?): EventProperties {
        add(property, value)
        return this
    }

}