package com.intutu.wap

import retrofit2.Call
import retrofit2.http.GET

interface RetroApi {
    @GET("data/2.5/onecall?lat=-37&lon=144&exclude=hourly,alerts,minutely&appid=1f3697a3116bbe570ad8d3b67d3a299b")
    fun getWeather(): Call<JkList>
}