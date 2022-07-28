package com.example.hse_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.Toolbar

class ActivityViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewer)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Settings"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
    fun onApply(view : View) {
        var edittext = findViewById<EditText>(R.id.idTVCourseDuration)
        val st : String = edittext.text.toString()
        try {
            var k : Float = st.toFloat()
            if (k >= 5) k = (5.0).toFloat()
            if (k < 0.2) k = (0.2).toFloat()
            koef = k
        }
        catch (e : Error) {
            // пусто
        }
        catch (e : Exception) {
            // пусто
        }
        val goto_main = Intent(this, MainActivity::class.java)
        startActivity(goto_main)
    }
}