package com.pahanez.ppt.network.dto

import com.pahanez.ppt.models.Stock
import com.pahanez.ppt.models.StockPayload
import com.squareup.moshi.Json
import java.time.LocalDateTime
import java.time.ZoneOffset

internal data class StockDto(
    @field:Json(name = "Meta Data") val metaData: StockMetaData,
    @field:Json(name = "Time Series FX (5min)") val payLoad: Map<LocalDateTime, FXPayloadDto>
)

internal data class StockMetaData(
    @field:Json(name = "1. Information") val info: String
)

internal data class FXPayloadDto(
    @field:Json(name = "1. open") val open: String,
    @field:Json(name = "2. high") val high: String,
    @field:Json(name = "3. low") val low: String,
    @field:Json(name = "4. close") val close: String
)

internal fun StockDto.toStock(): Stock {

    val payload = this.payLoad.map { entry ->

        val timeInMillis = entry.key.toEpochSecond(ZoneOffset.UTC)

        StockPayload(
            date = timeInMillis,
            open = entry.value.open,
            high = entry.value.high,
            low = entry.value.low,
            close = entry.value.close
        )
    }

    return Stock(info = this.metaData.info, payload = payload)
}