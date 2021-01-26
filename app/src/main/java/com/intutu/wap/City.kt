package com.intutu.wap

import java.util.*

data class City(var temp: String)
{
    var dates : Date = Date()

    private val dailywaps = listOf(
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates),
            DailyWeather(dates)

    )


}
