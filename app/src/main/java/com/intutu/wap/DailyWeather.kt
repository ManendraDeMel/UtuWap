package com.intutu.wap

import java.util.*

data class DailyWeather(var date: Date ,
                        var isRaining: Boolean = false,
                        var temp : String = "Min/Max")
