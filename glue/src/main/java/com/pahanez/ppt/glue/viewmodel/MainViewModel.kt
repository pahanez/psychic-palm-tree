package com.pahanez.ppt.glue.viewmodel

import androidx.lifecycle.ViewModel
import com.pahanez.ppt.main.StockDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class MainViewModel @Inject constructor(val dataSource: StockDataSource): ViewModel() {

    init {
        println("main view model init")

        GlobalScope.async {
            val result = dataSource.getStockData()
            println("main view model init $result")
        }
    }

    override fun onCleared() {
        println("main view model clear")
        super.onCleared()
    }

}
