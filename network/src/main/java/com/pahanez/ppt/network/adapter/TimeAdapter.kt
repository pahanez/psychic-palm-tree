package com.pahanez.ppt.network.adapter

import com.pahanez.ppt.network.utils.TimeParser
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.time.LocalDateTime

internal class TimeAdapter: JsonAdapter<LocalDateTime>() {

    override fun fromJson(reader: JsonReader): LocalDateTime? {
        val date = reader.readJsonValue() as String
        return TimeParser.parse(date)
    }

    override fun toJson(writer: JsonWriter, value: LocalDateTime?) {
        writer.value(value?.toString())
    }

}