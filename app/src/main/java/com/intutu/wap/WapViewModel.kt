package com.intutu.wap

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WapViewModel : ViewModel( ) {

    val dailywapsLiveData: LiveData<List<jk>>
    init {
        dailywapsLiveData = City("Sydney").getWeather()
    }

}