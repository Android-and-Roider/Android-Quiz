package com.example.androidstudyproject.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import com.example.androidstudyproject.R
import com.example.androidstudyproject.databinding.ActivityListBinding
import com.example.androidstudyproject.view.adapter.MenuAdapter
import com.example.androidstudyproject.viewmodel.MainViewModel

class ListActivity : AppCompatActivity() {

    lateinit var binding: ActivityListBinding
    private val viewModel: MainViewModel by viewModels()

    private val adapter = MenuAdapter(itemClickListener = {

    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.listMenu.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        val searchItem = menu.findItem(R.id.item_search)
        val btnAdd = menu.findItem(R.id.item_add)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "메뉴 검색"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}