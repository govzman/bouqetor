package com.example.hse_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.GsonBuilder

class ActivityView : AppCompatActivity() {
    lateinit var name : String

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
        name = intent.getStringExtra("name") ?: ""
        if (name != "") {
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menuview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_info) {
            val myCreator = Intent(this, ActivityInfo::class.java)
            startActivity(myCreator)
        }
        else if (item.itemId == R.id.action_erase) {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_delete, null)
            val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
            val btnSave = view.findViewById<Button>(R.id.idBtnAccept)
            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            btnSave.setOnClickListener {
                MyDelete(name)
                dialog.dismiss()
                val goto_main = Intent(this, ActivitySearch::class.java)
                startActivity(goto_main)
            }
            dialog.setCancelable(false) // false
            dialog.setContentView(view)
            dialog.show()
        }
        return super.onOptionsItemSelected(item)
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
    fun MyDelete(name : String) {
        files.num -= 1
        files.all_files.remove(name)
        var pref = getSharedPreferences(name, Context.MODE_PRIVATE)
        pref.edit().clear().commit()
        MySave()
        files = LoadFiles()
    }
    fun MySave() {
        val gson = GsonBuilder().create()
        var pref = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        pref.edit()
            .putString("settings", gson.toJson(files))
            .apply()
        files = LoadFiles()
    }
    fun LoadFiles() : Files {
        val gson = GsonBuilder().create()
        val pref = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val tmp = pref.getString("settings", "")
        val current_file : Files = gson.fromJson(tmp, Files::class.java)
        return current_file
    }
}
