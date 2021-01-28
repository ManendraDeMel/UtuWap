package com.intutu.wap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class WapViewModel(lat : String, lon : String) : ViewModel( ) {

    var latitude : String = lat
    var longitude : String = lon
    val dailywapsLiveData: LiveData<List<jk>>
    lateinit var dailywaps : List<DailyWeather>

    val sydneydailywapsLiveData: LiveData<List<jk>>
    lateinit var Sydneydailywaps : List<DailyWeather>

    val hobartdailywapsLiveData: LiveData<List<jk>>
    lateinit var hobartdailywaps : List<DailyWeather>

    val perthdailywapsLiveData: LiveData<List<jk>>
    lateinit var perthdailywaps : List<DailyWeather>

    var citylocation : MutableLiveData<String> = MutableLiveData()

    var cityobject : City = City(lat , lon)
    var sydney : City = City((-33.8696).toString() , (151.20695).toString())
    var hobart : City = City((-42.88164).toString() , (147.33162).toString())
    var perth : City = City((-31.95264).toString() , (115.85741).toString())






    init {



        dailywapsLiveData = cityobject.getWeather()
        sydneydailywapsLiveData = sydney.getWeather()
        hobartdailywapsLiveData = hobart.getWeather()
        perthdailywapsLiveData = perth.getWeather()

        citylocation.value = ""

        var dates : Date = Date()



            dailywaps = listOf(
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString()),
                    DailyWeather(dates.toString())

            )

    }

    fun dd() {



        dailywaps = listOf(
                DailyWeather(dailywapsLiveData.value?.get(1)?.dt.toString(),dailywapsLiveData.value!!.get(1).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(1)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".") ),
                DailyWeather(dailywapsLiveData.value?.get(2)?.dt.toString(),dailywapsLiveData.value!!.get(2).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(2)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(3)?.dt.toString(),dailywapsLiveData.value!!.get(3).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(3)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(4)?.dt.toString(),dailywapsLiveData.value!!.get(4).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(4)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(5)?.dt.toString(),dailywapsLiveData.value!!.get(5).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(5)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(6)?.dt.toString(),dailywapsLiveData.value!!.get(6).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(6)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(7)?.dt.toString(),dailywapsLiveData.value!!.get(7).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(7)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(0)?.dt.toString(),dailywapsLiveData.value!!.get(0).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(0)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(0)?.dt.toString(),dailywapsLiveData.value!!.get(0).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(0)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(0)?.dt.toString(),dailywapsLiveData.value!!.get(0).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(0)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(0)?.dt.toString(),dailywapsLiveData.value!!.get(0).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(0)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(0)?.dt.toString(),dailywapsLiveData.value!!.get(0).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(0)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(0)?.dt.toString(),dailywapsLiveData.value!!.get(0).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(0)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(0)?.dt.toString(),dailywapsLiveData.value!!.get(0).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(0)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(0)?.dt.toString(),dailywapsLiveData.value!!.get(0).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(0)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore(".")),
                DailyWeather(dailywapsLiveData.value?.get(0)?.dt.toString(),dailywapsLiveData.value!!.get(0).weather[0].main.toString(),temp = dailywapsLiveData.value?.get(0)?.temp?.min.toString().substringBefore(".") + "/" + dailywapsLiveData.value?.get(1)?.temp?.max.toString().substringBefore("."))

        )
        citylocation.value =cityobject.getCitylocationname()
        Log.d("TAG", "CITY NAME : $citylocation")


    }

}