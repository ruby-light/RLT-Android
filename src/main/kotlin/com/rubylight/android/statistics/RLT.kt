package com.rubylight.android.statistics

import android.content.Context

/**
 * Singleton pattern.
 */
object RLT {

    internal val wrapper = ClientWrapper()

    fun initialize(context: Context, apiKey: String): RLTClient {
        return initialize(context, apiKey, InitConfig())
    }

    @Synchronized
    fun initialize(context: Context, apiKey: String, config: InitConfig): RLTClient {
        if (wrapper.client != null) {
            warning { "Ignore initialize. RLT yet initialized." }
        } else {
            try {
                wrapper.client = ClientImpl(context, apiKey, config)
            } catch (th: Throwable) {
                error({ String.format("Error while initialize RLT, config: {}", config) }, th)
            }
        }

        return wrapper
    }

    fun getClient(): RLTClient {
        return wrapper
    }
}