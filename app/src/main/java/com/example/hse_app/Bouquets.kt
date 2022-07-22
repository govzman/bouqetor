package com.example.hse_app

import android.os.Environment
import com.google.gson.GsonBuilder
import java.io.File
import android.content.Context

data class Bouquets (var name : String, var radius : Int = 100, var all_cnt : Int = 0, var text : String = "") {
    var current_flowers : MutableList<Flower> = ArrayList()
    var cnt_flowers : MutableList<Int> = ArrayList()
    init {
        for (i in 0 until Flowers.values().size) cnt_flowers += 0
    }
}