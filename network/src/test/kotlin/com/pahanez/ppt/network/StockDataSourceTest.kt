package com.pahanez.ppt.network

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class StockDataSourceTest {

    lateinit var stockDataSource: StockDataSource

    @Before
    fun beforeEachTest() {
        stockDataSource = StockDataSourceImpl()
    }

    @Test
    fun testOne() {
        runBlocking {
            val stock = stockDataSource.getStockDataAsync().await()
            println("stock info: ${stock.info}")
            println("stock payload size: ${stock.payload.size}")
        }

    }
}