package com.intutu.wap

import retrofit2.Call
import retrofit2.http.GET

interface RetroApi {
    @GET("/")
    fun getWeather(): Call<String>
}