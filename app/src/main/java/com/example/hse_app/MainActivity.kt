package com.example.hse_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            files = LoadFiles()
        }
        catch (e : Exception) {
            files = Files()
        }
    }
    fun onCreator(view : View) {
        val myCreator = Intent(this, ActivityCreate::class.java)
        startActivity(myCreator)
    }
    fun onBouquets(view : View) {
        val myBouquets = Intent(this, ActivitySearch::class.java)
        startActivity(myBouquets)
    }
    fun onViewer(view : View) {
        val myViewer = Intent(this, ActivityViewer::class.java)
        startActivity(myViewer)
    }
    fun on_view_tmp(view : View) {
        val myBouquets = Intent(this, ActivityView::class.java)
        startActivity(myBouquets)
    }
    fun LoadFiles() : Files {
        val gson = GsonBuilder().create()
        val pref = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val tmp = pref.getString("settings", "")
        val current_file : Files = gson.fromJson(tmp, Files::class.java)
        return current_file
    }
}