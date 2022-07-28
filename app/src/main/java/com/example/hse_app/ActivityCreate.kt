package com.example.hse_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.GsonBuilder
import kotlin.math.pow


class ActivityCreate : AppCompatActivity(), View.OnTouchListener, View.OnClickListener {
    private var dX : Float = 0.toFloat()
    private var dY : Float = 0.toFloat()
    var current_bouquet : Bouquets = Bouquets("null")
    var all_id : MutableList<Int> = ArrayList()
    var all_types : MutableList<Flowers> = ArrayList()
    var global_id = 1
    var doubleClick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        // удаление цветков за границей экрана
        // свайпает в неправильную сторону
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Creator"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            if (all_id.size != 0) {
                for (i in 0 until all_id.size) {
                    var current_flower: Flower = Flower(
                        all_types[i],
                        findViewById<ImageView>(all_id[i]).x,
                        findViewById<ImageView>(all_id[i]).y,
                        findViewById<ImageView>(all_id[i]).height
                    )
                    if (all_types[i] == Flowers.rose) current_bouquet.cnt_flowers[0] += 1
                    else if (all_types[i] == Flowers.chamomile) current_bouquet.cnt_flowers[1] += 1
                    else if (all_types[i] == Flowers.carnation) current_bouquet.cnt_flowers[2] += 1
                    else if (all_types[i] == Flowers.chrysanthemum) current_bouquet.cnt_flowers[3] += 1
                    else if (all_types[i] == Flowers.peony) current_bouquet.cnt_flowers[4] += 1
                    else if (all_types[i] == Flowers.iris) current_bouquet.cnt_flowers[5] += 1
                    else if (all_types[i] == Flowers.lily) current_bouquet.cnt_flowers[6] += 1
                    else if (all_types[i] == Flowers.hortensia) current_bouquet.cnt_flowers[7] += 1
                    else if (all_types[i] == Flowers.sunflower) current_bouquet.cnt_flowers[8] += 1
                    else if (all_types[i] == Flowers.ruscus) current_bouquet.cnt_flowers[9] += 1
                    else if (all_types[i] == Flowers.dianthus) current_bouquet.cnt_flowers[10] += 1
                    else if (all_types[i] == Flowers.trachelium) current_bouquet.cnt_flowers[10] += 1
                    current_bouquet.current_flowers += current_flower
                }
                val dialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
                val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
                val btnSave = view.findViewById<Button>(R.id.idBtnAccept)
                val txtv = view.findViewById<TextView>(R.id.idTVCourseTracks)
                txtv.setText("Total flowers: ${current_bouquet.all_cnt}")
                btnClose.text = "Close"
                btnClose.setOnClickListener {
                    dialog.dismiss()
                }
                btnSave.setOnClickListener {
                    var edittext = view.findViewById<EditText>(R.id.idTVCourseDuration)
                    if (edittext.text.toString() !in bans && edittext.text.toString() !in files.all_files && edittext.text.toString() != "") {
                        current_bouquet.name = edittext.text.toString()
                        MySave(current_bouquet)
                        val goto_main = Intent(this, MainActivity::class.java)
                        startActivity(goto_main)
                    }

                }
                dialog.setCancelable(false) // false
                dialog.setContentView(view)
                dialog.show()
            }
        }
        else if (item.itemId == R.id.action_delete) {
            if (global_id != 1) {
                var newView: ImageView = findViewById(global_id - 1)
                var create_layout: ConstraintLayout = findViewById(R.id.create_layout)
                create_layout.removeView(newView)
                global_id -= 1
                current_bouquet.all_cnt -= 1
                all_id.removeLast()
                all_types.removeLast()
                Log.i("!!!", all_id.toString())
                Log.i("!!!", all_types.toString())
            }
        }
        else if (item.itemId == android.R.id.home){
            Log.i("BACK", "HOME")

            if (all_id.size != 0) {
                for (i in 0 until all_id.size) {
                    var current_flower: Flower = Flower(
                        all_types[i],
                        findViewById<ImageView>(all_id[i]).x,
                        findViewById<ImageView>(all_id[i]).y,
                        findViewById<ImageView>(all_id[i]).height
                    )
                    if (all_types[i] == Flowers.rose) current_bouquet.cnt_flowers[0] += 1
                    else if (all_types[i] == Flowers.chamomile) current_bouquet.cnt_flowers[1] += 1
                    else if (all_types[i] == Flowers.carnation) current_bouquet.cnt_flowers[2] += 1
                    else if (all_types[i] == Flowers.chrysanthemum) current_bouquet.cnt_flowers[3] += 1
                    else if (all_types[i] == Flowers.peony) current_bouquet.cnt_flowers[4] += 1
                    else if (all_types[i] == Flowers.iris) current_bouquet.cnt_flowers[5] += 1
                    else if (all_types[i] == Flowers.lily) current_bouquet.cnt_flowers[6] += 1
                    else if (all_types[i] == Flowers.hortensia) current_bouquet.cnt_flowers[7] += 1
                    else if (all_types[i] == Flowers.sunflower) current_bouquet.cnt_flowers[8] += 1
                    else if (all_types[i] == Flowers.ruscus) current_bouquet.cnt_flowers[9] += 1
                    else if (all_types[i] == Flowers.dianthus) current_bouquet.cnt_flowers[10] += 1
                    else if (all_types[i] == Flowers.trachelium) current_bouquet.cnt_flowers[10] += 1
                    current_bouquet.current_flowers += current_flower
                }
                var autosafe_id = 0
                while ("Autosave " + autosafe_id.toString() in files.all_files){
                    autosafe_id++
                }
                current_bouquet.name = "Autosave " + autosafe_id.toString()
                MySave(current_bouquet)
                val goto_main = Intent(this, MainActivity::class.java)
                startActivity(goto_main)

            }
            else {
                val intent = Intent(
                    this,
                    MainActivity::class.java
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                return true
            }
        }
        else if (item.itemId != R.id.filter_flowers) {
            var newView: ImageView
            newView = ImageView(this)
            var create_layout: ConstraintLayout = findViewById(R.id.create_layout)
            create_layout.addView(newView)
            newView.layoutParams.height = (300 * koef).toInt()
            newView.layoutParams.width = (300 * koef).toInt()
            newView.setId(global_id)
            all_id += global_id
            global_id += 1
            current_bouquet.all_cnt += 1
            newView.setOnTouchListener(this)
            newView.setOnClickListener(this)
            if (item.itemId == R.id.action_rose) {
                newView.setImageResource(R.drawable.rose)
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.rose
            } else if (item.itemId == R.id.action_chamomile) {
                newView.setImageResource(R.drawable.chamomile)
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.chamomile
            } else if (item.itemId == R.id.action_carnation) {
                newView.setImageResource(R.drawable.carnation)
                newView.layoutParams.height = (320 * koef).toInt()
                newView.layoutParams.width = (320 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.carnation
            } else if (item.itemId == R.id.action_chrysanthemum) {
                newView.setImageResource(R.drawable.chrysanthemum)
                newView.layoutParams.height = (340 * koef).toInt()
                newView.layoutParams.width = (340 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.chrysanthemum
            } else if (item.itemId == R.id.action_peony) {
                newView.setImageResource(R.drawable.peony)
                newView.layoutParams.height = (300 * koef).toInt()
                newView.layoutParams.width = (300 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.peony
            } else if (item.itemId == R.id.action_iris) {
                newView.setImageResource(R.drawable.iris)
                newView.layoutParams.height = (280 * koef).toInt()
                newView.layoutParams.width = (280 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.iris
            } else if (item.itemId == R.id.action_lily) {
                newView.setImageResource(R.drawable.lily)
                newView.layoutParams.height = (280 * koef).toInt()
                newView.layoutParams.width = (280 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.lily
            } else if (item.itemId == R.id.action_hortensia) {
                newView.setImageResource(R.drawable.hortensia)
                newView.layoutParams.height = (340 * koef).toInt()
                newView.layoutParams.width = (340 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.hortensia
            } else if (item.itemId == R.id.action_sunflower) {
                newView.setImageResource(R.drawable.sunflower)
                newView.layoutParams.height = (340 * koef).toInt()
                newView.layoutParams.width = (340 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.sunflower
            }
            else if (item.itemId == R.id.action_gypsophila) {
                newView.setImageResource(R.drawable.gypsophila)
                newView.layoutParams.height = (450 * koef).toInt()
                newView.layoutParams.width = (450 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.gypsophila
            }
            else if (item.itemId == R.id.action_ruscus) {
                newView.setImageResource(R.drawable.ruscus)
                newView.layoutParams.height = (500 * koef).toInt()
                newView.layoutParams.width = (500 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.ruscus
            }
            else if (item.itemId == R.id.action_dianthus) {
                newView.setImageResource(R.drawable.dianthus)
                newView.layoutParams.height = (400 * koef).toInt()
                newView.layoutParams.width = (400 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.dianthus
            }
            else if (item.itemId == R.id.action_trachelium) {
                newView.setImageResource(R.drawable.trachelium)
                newView.layoutParams.height = (320 * koef).toInt()
                newView.layoutParams.width = (320 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.trachelium
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                dX = view.x - event.rawX
                dY = view.y - event.rawY
            }
            MotionEvent.ACTION_MOVE -> view.animate()
                .x(event.rawX + dX)
                .y(event.rawY + dY)
                .setDuration(0)
                .start()
            else -> {
                val disp_centre_x = (this.getResources().getDisplayMetrics().widthPixels / 2) - 150
                val disp_centre_y = (this.getResources().getDisplayMetrics().heightPixels / 2) - 150
                if (((view.x - disp_centre_x).pow(2.0f) + (view.y - disp_centre_y).pow(2.0f)).pow(0.5f) < 400){
                    Log.i("is out?", "False")
                }
                else{
                    Log.i("is out?", "True")

                    var create_layout: ConstraintLayout = findViewById(R.id.create_layout)
                    view.setOnTouchListener(null)
                    create_layout.removeView(view)
                    current_bouquet.all_cnt -= 1
                    val ind = all_id.indexOf(view.id)
                    all_id.removeAt(ind)
                    all_types.removeAt(ind)
                    Log.i("!!!", ind.toString())
                }
                //Log.i("Radius is", ((view.x - disp_centre_x).pow(2.0f) + (view.y - disp_centre_y).pow(2.0f)).pow(0.5f).toString())
                return false
            }
        }
        return false
    }

    override fun onClick(view: View) {
        Log.i("HERE", "")
        if (doubleClick!!) {
            Log.i("DOUBLE", "Click")
            var newView: ImageView
            newView = ImageView(this)
            var create_layout: ConstraintLayout = findViewById(R.id.create_layout)
            create_layout.addView(newView)
            newView.layoutParams.height = (300 * koef).toInt()
            newView.layoutParams.width = (300 * koef).toInt()
            newView.setId(global_id)
            all_id += global_id
            global_id += 1
            current_bouquet.all_cnt += 1
            newView.setOnTouchListener(this)
            newView.setOnClickListener(this)
            val ind = all_id.indexOf(view.id)
            val item = all_types[ind]
            Log.i("H", item.toString())
            if (item == Flowers.rose) {
                newView.setImageResource(R.drawable.rose)
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.rose
            } else if (item == Flowers.chamomile) {
                newView.setImageResource(R.drawable.chamomile)
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.chamomile
            } else if (item == Flowers.carnation) {
                newView.setImageResource(R.drawable.carnation)
                newView.layoutParams.height = (320 * koef).toInt()
                newView.layoutParams.width = (320 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.carnation
            } else if (item == Flowers.chrysanthemum) {
                newView.setImageResource(R.drawable.chrysanthemum)
                newView.layoutParams.height = (340 * koef).toInt()
                newView.layoutParams.width =(340 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.chrysanthemum
            } else if (item == Flowers.peony) {
                newView.setImageResource(R.drawable.peony)
                newView.layoutParams.height = (300 * koef).toInt()
                newView.layoutParams.width = (300 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.peony
            } else if (item == Flowers.iris) {
                newView.setImageResource(R.drawable.iris)
                newView.layoutParams.height = (280 * koef).toInt()
                newView.layoutParams.width = (280 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.iris
            } else if (item == Flowers.lily) {
                newView.setImageResource(R.drawable.lily)
                newView.layoutParams.height = (280 * koef).toInt()
                newView.layoutParams.width = (280 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.lily
            } else if (item == Flowers.hortensia) {
                newView.setImageResource(R.drawable.hortensia)
                newView.layoutParams.height = (340 * koef).toInt()
                newView.layoutParams.width = (340 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.hortensia
            } else if (item == Flowers.sunflower) {
                newView.setImageResource(R.drawable.sunflower)
                newView.layoutParams.height = (380 * koef).toInt()
                newView.layoutParams.width = (380 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.sunflower
            }
            else if (item == Flowers.gypsophila) {
                newView.setImageResource(R.drawable.gypsophila)
                newView.layoutParams.height = (450 * koef).toInt()
                newView.layoutParams.width = (450 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.gypsophila
            }
            else if (item == Flowers.ruscus) {
                newView.setImageResource(R.drawable.ruscus)
                newView.layoutParams.height = (500 * koef).toInt()
                newView.layoutParams.width = (500 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.ruscus
            }
            else if (item == Flowers.dianthus) {
                newView.setImageResource(R.drawable.dianthus)
                newView.layoutParams.height = (400 * koef).toInt()
                newView.layoutParams.width = (400 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.dianthus
            }
            else if (item == Flowers.trachelium) {
                newView.setImageResource(R.drawable.trachelium)
                newView.layoutParams.height = (320 * koef).toInt()
                newView.layoutParams.width = (320 * koef).toInt()
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.trachelium
            }
            newView.x = view.x + 50
            newView.y = view.y + 50
        }
        doubleClick = true
        Handler().postDelayed({ doubleClick = false }, 400)
    }

    fun MySave(bouq : Bouquets) {
        val gson = GsonBuilder().create()
        var pref = getSharedPreferences(bouq.name, Context.MODE_PRIVATE)
        pref.edit()
            .putString("Bouquet", gson.toJson(bouq))
            .apply()
        files.num += 1
        files.all_files += bouq.name
        pref = getSharedPreferences("Settings", Context.MODE_PRIVATE)
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



