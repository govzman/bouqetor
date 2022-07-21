package com.example.hse_app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout

class ActivityCreate : AppCompatActivity(), View.OnTouchListener {
    private var dX : Float = 0.toFloat()
    private var dY : Float = 0.toFloat()
    var current_bouquet : Bouquets = Bouquets("First")
    var all_id : MutableList<Int> = ArrayList()
    var all_types : MutableList<Flowers> = ArrayList()
    var global_id = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Bouquets Creator"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            if (all_id.size != 0) {
                for (i in 0 until all_id.size) {
                    current_bouquet.all_cnt += 1
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


                    current_bouquet.current_flowers += current_flower
                }
                current_bouquet.MySave()
            }
        }
        else {
            var newView: ImageView
            newView = ImageView(this)
            var create_layout: ConstraintLayout = findViewById(R.id.create_layout)
            create_layout.addView(newView)
            newView.layoutParams.height = 300
            newView.layoutParams.width = 300
            newView.setId(global_id)
            all_id += global_id
            global_id += 1
            newView.setOnTouchListener(this)
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
                newView.layoutParams.height = 320
                newView.layoutParams.width = 320
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.carnation
            } else if (item.itemId == R.id.action_chrysanthemum) {
                newView.setImageResource(R.drawable.chrysanthemum)
                newView.layoutParams.height = 340
                newView.layoutParams.width =340
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.chrysanthemum
            } else if (item.itemId == R.id.action_peony) {
                newView.setImageResource(R.drawable.peony)
                newView.layoutParams.height = 300
                newView.layoutParams.width = 300
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.peony
            } else if (item.itemId == R.id.action_iris) {
                newView.setImageResource(R.drawable.iris)
                newView.layoutParams.height = 280
                newView.layoutParams.width = 280
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.iris
            } else if (item.itemId == R.id.action_lily) {
                newView.setImageResource(R.drawable.lily)
                newView.layoutParams.height = 280
                newView.layoutParams.width = 280
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.lily
            } else if (item.itemId == R.id.action_hortensia) {
                newView.setImageResource(R.drawable.hortensia)
                newView.layoutParams.height = 340
                newView.layoutParams.width = 340
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.hortensia
            } else if (item.itemId == R.id.action_sunflower) {
                newView.setImageResource(R.drawable.sunflower)
                newView.layoutParams.height = 380
                newView.layoutParams.width = 380
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.sunflower
            }
            else if (item.itemId == R.id.action_gypsophila) {
                newView.setImageResource(R.drawable.gypsophila)
                newView.layoutParams.height = 450
                newView.layoutParams.width = 450
                newView.x = (this.getResources()
                    .getDisplayMetrics().widthPixels / 2 - newView.layoutParams.height / 2).toFloat()
                newView.y = (this.getResources()
                    .getDisplayMetrics().heightPixels / 2 - newView.layoutParams.width / 2).toFloat()
                all_types += Flowers.gypsophila
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
            else -> return false
        }
        return true
    }
}


