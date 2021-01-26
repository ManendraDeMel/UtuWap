package com.intutu.wap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

private const val TAG = "RetroCall"


data class City(var temp: String)
{


        private val retroApi: RetroApi

        init {
            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://www.flickr.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
            retroApi = retrofit.create(RetroApi::class.java)
            
        }





    fun getWeather(): LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val flickrRequest: Call<String> = retroApi.getWeather()
        flickrRequest.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }
            override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
            ) {
                Log.d(TAG, "Response received")
                responseLiveData.value = response.body()
            }
        })
        return responseLiveData
    }


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

