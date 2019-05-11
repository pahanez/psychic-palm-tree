package com.pahanez.ppt.network.utils

import okhttp3.Interceptor
import okhttp3.Response

class RetryInterceptor : Interceptor {

    companion object {
        private const val RETRY_COUNT = 2
        private const val RETRY_DELAY_MS = 500L
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(chain.request())
        var retryCount = 0

        while (response.code() in 500 until 600 && retryCount < RETRY_COUNT) {
            try {
                Thread.sleep(RETRY_DELAY_MS)
            } catch (ex: InterruptedException) {
                break
            }

            response = chain.proceed(chain.request().newBuilder().build())

            retryCount++
        }
        return response
    }
}