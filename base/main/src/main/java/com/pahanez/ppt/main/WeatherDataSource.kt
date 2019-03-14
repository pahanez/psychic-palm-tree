package com.pahanez.ppt.main

import com.pahanez.ppt.models.Weather

interface WeatherDataSource {
    fun getWeather(): Weather
}