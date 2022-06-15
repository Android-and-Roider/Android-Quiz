package com.example.androidstudyproject.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.androidstudyproject.R
import com.example.androidstudyproject.databinding.ActivityRaffleBinding

class RaffleActivity : AppCompatActivity() {

    lateinit var binding: ActivityRaffleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRaffleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        overridePendingTransition(R.anim.slide_enter,R.anim.none)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.anim.none,R.anim.slide_down)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}