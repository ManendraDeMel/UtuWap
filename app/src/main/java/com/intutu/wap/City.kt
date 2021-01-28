package com.intutu.wap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

private const val TAG = "RetroCall"


data class City(var lat: String , var lon : String)
{


        private val retroApi: RetroApi
        private var cityurl : String
        var cityname : String = "loc"

        init {
            cityurl = "data/2.5/onecall?"+ "lat=" + lat + "&lon=" + lon + "&exclude=hourly,alerts,minutely&units=metric&appid=1f3697a3116bbe570ad8d3b67d3a299b"
            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            retroApi = retrofit.create(RetroApi::class.java)

            getWeather()

        }





    fun getWeather(): LiveData<List<jk>> {
        val responseLiveData: MutableLiveData<List<jk>> = MutableLiveData()
        val flickrRequest: Call<JkList> = retroApi.getWeather(cityurl)
        flickrRequest.enqueue(object : Callback<JkList> {
            override fun onFailure(call: Call<JkList>, t: Throwable) {
                Log.e(TAG, "Failed Weather Request", t)
            }
            override fun onResponse(
                    call: Call<JkList>,
                    response: Response<JkList>
            ) {
                Log.d(TAG, "Response received")
                val jklistresponse: JkList? = response.body()
                var galleryItems: List<jk> = jklistresponse?.wapobjects
                    ?: mutableListOf()
                responseLiveData.value = galleryItems
                if (jklistresponse != null) {
                    cityname = jklistresponse.city
                }
                Log.d(TAG, "CITY NAME : $cityname")
            }
        })

        return responseLiveData
    }

    fun getCitylocationname() : String{

        return  cityname
    }





}

