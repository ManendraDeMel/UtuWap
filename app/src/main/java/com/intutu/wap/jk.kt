package com.intutu.wap

data class jk(


    val dt: Int,
    val feels_like: FeelsLike,
    val humidity: Int,
    val pop: Float,
    val pressure: Int,
    val rain: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_speed: Double
)

data class FeelsLike(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)

data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)