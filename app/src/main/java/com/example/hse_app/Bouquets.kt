package com.example.hse_app

class Bouquets (var radius : Int = 100, var all_cnt : Int = 0, var text : String = ""){
    var current_flowers : MutableList<Flower> = ArrayList()
    var cnt_flowers : MutableList<Int> = ArrayList()
    init {
        for (i in 0 until Flowers.values().size) cnt_flowers += 0
    }
}