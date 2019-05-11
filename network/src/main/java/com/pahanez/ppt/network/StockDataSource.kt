package com.pahanez.ppt.network

import com.pahanez.ppt.models.Stock
import com.pahanez.ppt.network.dto.toStock
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

interface StockDataSource {
    fun getStockDataAsync(): Deferred<Stock>

}

internal class StockDataSourceImpl : StockDataSource {

    private val stockApi: StockApi = StockApiFactory.create()

    override fun getStockDataAsync(): Deferred<Stock> {
        return async {
            val response = stockApi.getStockData().execute()
            if(response.isSuccessful) {
                val body = response.body()
                if(body != null) {
                    return@async body.toStock()
                }
            }

            throw IllegalStateException()
        }
    }

}
