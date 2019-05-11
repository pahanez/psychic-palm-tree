package com.pahanez.ppt.network

import com.pahanez.ppt.network.dto.StockDto
import retrofit2.Call
import retrofit2.http.GET

//APIKEY: TFDSOR397RJJ4569
internal interface StockApi {

    @GET("/query?function=FX_INTRADAY&from_symbol=EUR&to_symbol=USD&interval=5min&outputsize=full&apikey=TFDSOR397RJJ4569")
    fun getStockData(): Call<StockDto>

}