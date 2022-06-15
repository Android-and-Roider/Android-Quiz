package com.example.androidstudyproject.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidstudyproject.R
import com.example.androidstudyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnMenuList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java).apply {
                startActivity(this)
            }
        }
        binding.btnStartChoose.setOnClickListener {
            val intent = Intent(this, RaffleActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}