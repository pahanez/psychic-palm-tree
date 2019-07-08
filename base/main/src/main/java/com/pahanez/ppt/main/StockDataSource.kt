package com.pahanez.ppt.main

import com.pahanez.ppt.models.Stock

interface StockDataSource {
    suspend fun getStockData(): Stock

}