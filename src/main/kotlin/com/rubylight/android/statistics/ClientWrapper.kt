package com.rubylight.android.statistics

/**
 * Wrapper.
 */
class ClientWrapper : RLTClient {

    internal var client: RLTClient? = null

    override fun setUserId(userId: String): RLTClient {
        client?.setUserId(userId) ?: ignoreWarning("setUserId")
        return this
    }

    override fun resetUserId(regenerateDeviceId: Boolean): RLTClient {
        client?.resetUserId(regenerateDeviceId) ?: ignoreWarning("resetUserId")
        return this
    }

    override fun logUserProperties(userProperties: UserProperties): RLTClient {
        client?.logUserProperties(userProperties) ?: ignoreWarning("logUserProperties")
        return this
    }

    override fun logEvent(eventName: String): RLTClient {
        client?.logEvent(eventName) ?: ignoreWarning("logEvent")
        return this
    }

    override fun logEvent(eventName: String, eventProperties: EventProperties): RLTClient {
        client?.logEvent(eventName, eventProperties) ?: ignoreWarning("logEvent")
        return this
    }

    override fun flush() {
        client?.flush() ?: ignoreWarning("flush")
    }

    override fun getDeviceId(): String {
        return client?.getDeviceId() ?: run {
            ignoreWarning("getDeviceId")
            return ""
        }
    }

    private fun ignoreWarning(method: String) {
        warning { String.format("Ignore %s(), RLT not initialized", method) }
    }

}