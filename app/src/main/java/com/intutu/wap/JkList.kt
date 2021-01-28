package com.intutu.wap

import com.google.gson.annotations.SerializedName

class JkList {
    @SerializedName("daily")

    lateinit var wapobjects : List<jk>

    @SerializedName("timezone")

    lateinit var city : String
}