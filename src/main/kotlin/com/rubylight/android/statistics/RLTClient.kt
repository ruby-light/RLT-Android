package com.rubylight.android.statistics

/**
 * Client API.
 */
interface RLTClient {

    fun setUserId(userId: String): RLTClient

    fun resetUserId(regenerateDeviceId: Boolean): RLTClient

    fun logUserProperties(userProperties: UserProperties): RLTClient

    fun logEvent(eventName: String): RLTClient

    fun logEvent(eventName: String, eventProperties: EventProperties): RLTClient

    fun flush()
}