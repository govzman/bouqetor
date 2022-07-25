package com.example.hse_app

enum class Flowers{
    rose, chamomile, carnation, chrysanthemum, iris, peony, lily, sunflower, hortensia, gypsophila, ruscus, dianthus, trachelium
}

val bans : List<String> = listOf("Settings")
class Flower(val name: Flowers = Flowers.rose, var x: Float = 0.toFloat(), var y: Float = 0.toFloat(), var size: Int = 0)