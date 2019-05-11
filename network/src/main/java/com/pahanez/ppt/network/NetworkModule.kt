package com.pahanez.ppt.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    private val READ_WRITE_TIMEOUT_VALUE_SEC = 20L

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.readTimeout(READ_WRITE_TIMEOUT_VALUE_SEC, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(READ_WRITE_TIMEOUT_VALUE_SEC, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(READ_WRITE_TIMEOUT_VALUE_SEC, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

    @Provides
    internal fun provideApiService(client: OkHttpClient): StockApi =
        Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(createConverter())
            .baseUrl("https://www.alphavantage.co")
            .client(client)
            .build()
            .create(StockApi::class.java)

    private fun createConverter(): Converter.Factory =
        MoshiConverterFactory.create(Moshi.Builder().build())

    @Provides
    internal fun provideStockDataSource(stockApi: StockApi): StockDataSource {
        return StockDataSourceImpl()
    }

}