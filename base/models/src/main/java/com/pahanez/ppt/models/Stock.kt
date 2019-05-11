package com.pahanez.ppt.models

data class Stock(val info: String, val payload: List<StockPayload>)

data class StockPayload(
    val date: Long,
    val open: String,
    val high: String,
    val low: String,
    val close: String
)