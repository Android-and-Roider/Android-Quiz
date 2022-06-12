package com.example.androidstudyproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidstudyproject.databinding.ActivityRaffleBinding

class RaffleActivity : AppCompatActivity() {

    lateinit var binding: ActivityRaffleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRaffleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}