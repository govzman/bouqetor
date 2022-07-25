package com.example.hse_app

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class ActivitySearch : AppCompatActivity() {
    var displayList : MutableList<String> = ArrayList()

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
        displayList.addAll(files.all_files)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search, menu)
        val manager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.menu_search)
        val searchView = searchItem?.actionView as androidx.appcompat.widget.SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                val new_activity = Intent(this@ActivitySearch, ActivityView::class.java)
                new_activity.putExtra("name", query)
                startActivity(new_activity)
                //i.putExtra("key",value)
                /*Toast.makeText(this@MainActivity, "Looking for $query", Toast.LENGTH_LONG).show()
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
                }*/
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return true
    }
}
