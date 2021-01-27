package com.intutu.wap

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WapViewModel(lat : String, lon : String) : ViewModel( ) {

    var latitude : String = lat
    var longitude : String = lon
    val dailywapsLiveData: LiveData<List<jk>>

    init {
        dailywapsLiveData = City(lat , lon).getWeather()
    }

}