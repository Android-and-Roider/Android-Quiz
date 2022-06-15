package com.example.androidstudyproject.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.androidstudyproject.R
import com.example.androidstudyproject.data.Food
import com.example.androidstudyproject.databinding.ActivityListBinding
import com.example.androidstudyproject.view.adapter.MenuAdapter
import com.example.androidstudyproject.viewmodel.MainViewModel

class ListActivity : AppCompatActivity() {

    lateinit var binding: ActivityListBinding
    private val viewModel: MainViewModel by viewModels()

    private val adapter = MenuAdapter(itemClickListener = {
        doOnclick(it)
    })

    private lateinit var dialog: AddDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = AddDialog(this).apply {
            this.setOnClickListener(object : AddDialog.ButtonClickListener {
                override fun onClicked(
                    name: String,
                    meat: Boolean,
                    fruit: Boolean,
                    dairy: Boolean,
                    category: String
                ) {
                    val food = Food(name, category, meat, fruit, dairy)
                    viewModel.insertFood(food)
                }
            })
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        subscribeToObservables()

        binding.listMenu.adapter = adapter
        /*overridePendingTransition(R.anim.slide_enter,R.anim.none)*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                /*overridePendingTransition(R.anim.none,R.anim.slide_down)*/
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

    private fun subscribeToObservables() {
        viewModel.food.observe(this) { listFood ->
            listFood?.let { adapter.submitList(listFood.toList()) }
        }
    }

    private fun doOnclick(item: Food) {
        AlertDialog.Builder(this@ListActivity)
            .setTitle("삭제할까요")
            .setPositiveButton("예") { _, _ ->
                viewModel.deleteFood(item)
            }.setNegativeButton("아니오") { _, _ ->
            }
            .create()
            .show()
    }

}
