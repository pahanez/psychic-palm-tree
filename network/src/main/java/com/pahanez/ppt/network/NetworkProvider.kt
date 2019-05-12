package com.pahanez.ppt.network

import com.pahanez.ppt.main.StockDataSource

object NetworkProvider {

    /** Must be used only from dagger module */
    fun stockDataSource(): StockDataSource {
        return StockDataSourceImpl()
    }

}