package com.pahanez.ppt.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pahanez.ppt.network.adapter.TimeAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

internal object StockApiFactory {
    private const val BASE_URL = "https://www.alphavantage.co"
    private const val TIMEOUT = 10L

    fun create(url: String = BASE_URL) =
        createmApi(url, createOkHttpClient(createLoggingInterceptor()))

    private fun createmApi(url: String, okHttpClient: OkHttpClient): StockApi =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(createConverter())
            .build().create(StockApi::class.java)

    private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

    private fun createLoggingInterceptor(isDebug: Boolean = true): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
            if (isDebug) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private fun createConverter(): Converter.Factory {

        val moshi = Moshi.Builder()
            .add(LocalDateTime::class.java, TimeAdapter())
            .build()
        return MoshiConverterFactory.create(moshi)
    }


}
