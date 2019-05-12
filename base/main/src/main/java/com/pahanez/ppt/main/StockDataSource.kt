package com.pahanez.ppt.main

import com.pahanez.ppt.models.Stock
import kotlinx.coroutines.Deferred

interface StockDataSource {
    fun getStockDataAsync(): Deferred<Stock>

}