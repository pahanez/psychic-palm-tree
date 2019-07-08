package com.pahanez.ppt.network

import com.pahanez.ppt.main.StockDataSource
import com.pahanez.ppt.models.Stock
import com.pahanez.ppt.network.dto.toStock

internal class StockDataSourceImpl : StockDataSource {

    private val stockApi: StockApi = StockApiFactory.create()

    override suspend fun getStockData(): Stock {

            val response = stockApi.getStockData().execute()
            if(response.isSuccessful) {
                val body = response.body()
                if(body != null) {
                    return body.toStock()
                }
            }

            throw IllegalStateException()

    }

}
