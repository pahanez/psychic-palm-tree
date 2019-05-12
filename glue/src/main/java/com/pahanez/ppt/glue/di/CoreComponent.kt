package com.pahanez.ppt.glue.di

import androidx.lifecycle.ViewModelProvider
import com.pahanez.ppt.glue.viewmodel.ViewModelModule
import com.pahanez.ppt.main.StockDataSource
import dagger.Component

@Component(
    modules = [
        ViewModelModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent {
    @Component.Builder interface Builder {
        fun build(): CoreComponent
    }

    fun provideStockDataSource(): StockDataSource
    fun provideViewModelFactory(): ViewModelProvider.Factory
}