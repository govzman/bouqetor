package com.example.hse_app

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class ActivitySearch : AppCompatActivity() {
    var displayList : MutableList<String> = ArrayList()
    lateinit var mListView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        // android.view.WindowLeaked: Activity com.example.hse_app.ActivityView has leaked window DecorView@4d032a0[ActivityView] that was originally added here
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
        mListView = findViewById<ListView>(R.id.userlist)
        var arrayAdapter = ArrayAdapter(this,
            R.layout.mytextview, displayList)
        mListView.adapter = arrayAdapter
        mListView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val new_activity = Intent(this@ActivitySearch, ActivityView::class.java)
            new_activity.putExtra("name", displayList[id.toInt()])
            startActivity(new_activity)
        })
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
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                displayList.clear()
                if(newText!!.isNotEmpty()){
                    val search = newText.toLowerCase()
                    files.all_files.forEach {
                        if(it.toLowerCase().startsWith(search)){
                            displayList.add(it)
                        }
                    }
                }else{
                    displayList.addAll(files.all_files)
                }
                mListView.invalidateViews()
                return true
            }
        })
        return true
    }
}


