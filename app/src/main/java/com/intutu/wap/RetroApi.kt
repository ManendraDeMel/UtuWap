package com.intutu.wap

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface RetroApi {
    @GET
    fun getWeather(@Url url :String ): Call<JkList>

}