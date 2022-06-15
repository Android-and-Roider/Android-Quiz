package com.example.androidstudyproject.view

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.androidstudyproject.databinding.ActivityRaffleBinding
import com.example.androidstudyproject.viewmodel.MainViewModel

class RaffleActivity : AppCompatActivity() {

    lateinit var binding: ActivityRaffleBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRaffleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        subscribeToObservables()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.btnStartRaffle.setOnClickListener {
            viewModel.makeRandom()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                /*overridePendingTransition(R.anim.none, R.anim.slide_down)*/
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun subscribeToObservables() {
        viewModel.isLoading.observe(this) {
            if (it) {
                binding.apply {
                    this.selectedFood.isVisible = !it
                    this.loadingAnimationView.isVisible = it
                    this.btnStartRaffle.isEnabled = !it
                    this.loadingAnimationView.playAnimation()
                }
            } else {
                binding.apply {
                    this.selectedFood.isVisible = !it
                    this.loadingAnimationView.isVisible = it
                    this.btnStartRaffle.isEnabled = !it
                    this.loadingAnimationView.cancelAnimation()
                }
            }
        }
    }

}