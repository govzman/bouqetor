package com.example.hse_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onCreator(view : View) {
        val myCreator = Intent(this, MainActivity2::class.java)
        startActivity(myCreator)
    }
    fun onBouquets(view : View) {
        val myBouquets = Intent(this, MainActivity3::class.java)
        startActivity(myBouquets)
    }
}