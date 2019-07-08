package com.pahanez.ppt.network.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object  TimeParser {

    private val dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun parse(toParse: String): LocalDateTime {
        return LocalDateTime.parse(toParse, dateTimeFormatter2)
    }
}