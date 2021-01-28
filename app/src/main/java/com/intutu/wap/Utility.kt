package com.intutu.wap

import android.content.Context
import android.location.Geocoder
import java.text.SimpleDateFormat
import java.util.*

class Utility {

    fun getDateTime(s: String): String? {
        //try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val netDate = Date(s.toLong() * 1000)
            //return sdf.format(netDate)
        //} catch (e: Exception) {
         //   return e.toString()
        //}

        val myDate = sdf.parse(sdf.format(netDate))
        sdf.applyPattern("EEE, d MMM")
        val sMyDate = sdf.format(myDate)

        return sMyDate

    }

    fun getAddress(lat: Double, lng: Double, context: Context?): String {
        val geocoder = Geocoder(context)
        val list = geocoder.getFromLocation(lat, lng, 1)
        var address =  list.get(0).getAddressLine(0)

        //val splitAddress = address.split(",".toRegex()).toTypedArray() // Splits address where there is a space, creating array with 5 index. Index 4 should be CITY!


        //var city = splitAddress[2]  // Saves city into city-string, and removes a comma from the getAddressLine(0)!

        return address



    }

}