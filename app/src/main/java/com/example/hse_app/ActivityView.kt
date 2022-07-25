package com.example.hse_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.GsonBuilder

class ActivityView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "My Bouquets"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val name : String? = intent.getStringExtra("name")
        if (name != null) {
            var current_id = 1
            var current_bouq: Bouquets = MyRead(name)
            for (i in current_bouq.current_flowers) {
                var newView: ImageView
                newView = ImageView(this)
                var create_layout: ConstraintLayout = findViewById(R.id.create_layout)
                create_layout.addView(newView)
                newView.setId(current_id)
                current_id += 1
                newView.layoutParams.height = i.size
                newView.layoutParams.width = i.size
                newView.x = i.x
                newView.y = i.y
                val icon = when (i.name) {
                    Flowers.rose -> R.drawable.rose
                    Flowers.chamomile -> R.drawable.chamomile
                    Flowers.carnation -> R.drawable.carnation
                    Flowers.chrysanthemum -> R.drawable.chrysanthemum
                    Flowers.iris -> R.drawable.iris
                    Flowers.peony -> R.drawable.peony
                    Flowers.lily -> R.drawable.lily
                    Flowers.sunflower -> R.drawable.sunflower
                    Flowers.hortensia -> R.drawable.hortensia
                    Flowers.gypsophila -> R.drawable.gypsophila
                    Flowers.ruscus -> R.drawable.ruscus
                    Flowers.dianthus -> R.drawable.dianthus
                    Flowers.trachelium -> R.drawable.trachelium
                }
                newView.setImageResource(icon)
            }
        }
    }


    fun MyRead(name : String) : Bouquets {
        val gson = GsonBuilder().create()
        val pref = getSharedPreferences(name, Context.MODE_PRIVATE)
        val raw : String? = pref.getString("Bouquet", null)
        if (raw != null) {
            val bouq = gson.fromJson(raw, Bouquets::class.java)
            return bouq
        }
        return Bouquets("null")
    }
}
