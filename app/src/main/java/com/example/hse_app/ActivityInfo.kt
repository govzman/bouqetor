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
        var cur_t : String = ""
        var cnt = 0
        var price = 0
        for (i in Flowers_print.values()) {
            try {
                var cur_f: String = ""
                cur_f += i.toString()
                cur_f += ": "
                cur_f += cur_b.cnt_flowers[cnt].toString()
                price += cur_b.cnt_flowers[cnt] * costs[cnt]
                cur_f += "\n"
                if (cur_b.cnt_flowers[cnt] != 0) cur_t += cur_f
                ++cnt
            }
            catch(e : Exception) {
                break
            }
        }
        cur_t += "Total price: "
        cur_t += price.toString()
        cur_t += " rub"
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
