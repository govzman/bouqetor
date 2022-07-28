package com.example.hse_app

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.gson.GsonBuilder

class ActivityInfo : AppCompatActivity() {
    var cur_b : Bouquets = Bouquets("null")
    var name : String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityinfo)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Information"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        name = intent.getStringExtra("name") ?: ""
        var raw : String? = intent.getStringExtra("bouq") ?: null
        if (raw != null) {
            val gson = GsonBuilder().create()
            cur_b = gson.fromJson(raw, Bouquets::class.java)
        }
        var txtv : TextView = findViewById(R.id.textView)
        var cur_list : MutableList<Int> = ArrayList()
        for (i in 0 until Flowers.values().size) cur_list += 0
        var cur_t : String = ""
        var price = 0
        for (i in 0 until cur_list.size) {
            var cur_f: String = ""
            cur_f += Flowers.values()[i].toString()
            cur_f += ": "
            cur_f += cur_list[i].toString()
            price += cur_list[i] * costs[i]
            cur_f += "\n"
            if (cur_list[i] != 0) cur_t += cur_f
        }
        cur_t += "Total price: "
        cur_t += price.toString()
        cur_t += "₽"
        txtv.setText(cur_t)
    }
    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val new_activity = Intent(this@ActivityInfo, ActivityView::class.java)
                new_activity.putExtra("name", name)
                startActivity(new_activity)
            }
        }
        return true
    }
}
