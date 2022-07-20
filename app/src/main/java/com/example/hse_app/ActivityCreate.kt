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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var newView: ImageView
        newView = ImageView(this)
        var create_layout : ConstraintLayout = findViewById(R.id.create_layout)
        create_layout.addView(newView)
        newView.layoutParams.height = 200
        newView.layoutParams.width = 200
        newView.x = (this.getResources().getDisplayMetrics().widthPixels / 2 - 100).toFloat()
        newView.y = (this.getResources().getDisplayMetrics().heightPixels / 2 - 100).toFloat()
        newView.setId(View.generateViewId())
        newView.setOnTouchListener(this)
        if (item.itemId == R.id.action_rose) {
            newView.setImageResource(R.drawable.rose)
        }
        else if (item.itemId == R.id.action_chamomile) {
            newView.setImageResource(R.drawable.chamomile)
        }
        else {
            TODO()
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


