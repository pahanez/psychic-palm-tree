package com.pahanez.ppt.network

import com.pahanez.ppt.main.StockDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class StockDataSourceTest {

    lateinit var stockDataSource: StockDataSource

    @Before
    fun beforeEachTest() {
        stockDataSource = StockDataSourceImpl()
    }

    @Test
    fun testOne() = runBlocking {
            val stock = stockDataSource.getStockData()
            Assert.assertNotNull(stock)
    }
}