package com.pahanez.ppt.glue.di

import com.pahanez.ppt.main.StockDataSource
import com.pahanez.ppt.network.NetworkProvider
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideStockDataSource(): StockDataSource {
        return NetworkProvider.stockDataSource()
    }

}