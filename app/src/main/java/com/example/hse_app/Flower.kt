package com.example.hse_app

enum class Flowers{
    rose, chamomile, carnation, chrysanthemum, iris, peony, lily, sunflower, hortensia, gypsophila, ruscus, dianthus, trachelium
}

enum class Flowers_print {
    Roses, Chamomiles, Carnations, Chrysanthemums, Irises, Peonies, Lilies, Sunflowers, Hortensias, Gypsophilas, Ruscuses, Dianthuses, Tracheliums
}
val bans : List<String> = listOf("Settings", "Menu_settings")
var costs : List<Int> = listOf(150, 120, 60, 150, 80, 300, 525, 120, 525, 150, 60, 120, 120)
var koef : Float = (1.0).toFloat()

class Flower(val name: Flowers = Flowers.rose, var x: Float = 0.toFloat(), var y: Float = 0.toFloat(), var size: Int = 0)