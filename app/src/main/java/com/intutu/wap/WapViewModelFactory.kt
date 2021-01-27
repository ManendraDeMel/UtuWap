package com.intutu.wap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WapViewModelFactory(private val lat : String , private val lon : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WapViewModel::class.java)) {
            return WapViewModel(lat,lon) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}