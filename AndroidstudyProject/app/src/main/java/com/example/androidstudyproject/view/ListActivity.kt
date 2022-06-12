package com.example.androidstudyproject.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
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
    private val adapter = MenuAdapter {
        openPopup()
    }
    private lateinit var dialog: AddDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = AddDialog(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.listMenu.adapter = adapter
        binding.listMenu.setOnClickListener { }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        val searchItem = menu.findItem(R.id.item_search)
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
            R.id.item_add -> {
                dialog.show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun openPopup(){
        val popupMenu = PopupMenu(applicationContext, View(this))
        menuInflater?.inflate(R.menu.list_popup, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.delete ->{
                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener true
                }
            }
        }
    }
}