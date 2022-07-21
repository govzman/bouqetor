package com.example.hse_app

import android.os.Environment
import java.io.File

class Bouquets (var name : String, var radius : Int = 100, var all_cnt : Int = 0, var text : String = ""){
    var current_flowers : MutableList<Flower> = ArrayList()
    var cnt_flowers : MutableList<Int> = ArrayList()
    init {
        for (i in 0 until Flowers.values().size) cnt_flowers += 0
    }
    fun MySave() {
        val file = File((Environment.getExternalStorageDirectory().toString() + "/flowersettings/" + this.name +".txt"))
        file.createNewFile()
        file.writeText("This will be written to the file!")
    }
}