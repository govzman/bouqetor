package com.example.hse_app

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class ActivitySearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Search Bouquets"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search, menu)
        /*val manager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                Toast.makeText(this@MainActivity, "Looking for $query", Toast.LENGTH_LONG).show()
                val ind = query?.let { names.indexOf(it.lowercase()) }
                if (ind == -1) {
                    tx.setTextSize(TypedValue.COMPLEX_UNIT_SP, (32).toFloat())
                    tx.setText("Not found")
                    tx.setVisibility(TextView.VISIBLE)
                    im.setVisibility(ImageView.INVISIBLE)
                }
                else {
                    tx.setTextSize(TypedValue.COMPLEX_UNIT_SP, (32).toFloat())
                    if (ind != null) {
                        tx.setText("Name: ${characters[ind].name}\nSpecies: ${characters[ind].species}\nGender: ${characters[ind].gender}\n" +
                                "Status: ${characters[ind].status}\nId: ${characters[ind].id}")
                        im.setImageBitmap(characters[ind].img)
                        im.setVisibility(ImageView.VISIBLE)
                    }
                    tx.setVisibility(TextView.VISIBLE)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })*/
        return true
    }
}