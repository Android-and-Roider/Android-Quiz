package com.example.androidstudyproject.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.androidstudyproject.databinding.ActivityRaffleBinding
import com.example.androidstudyproject.viewmodel.MainViewModel

class RaffleActivity : AppCompatActivity() {

    lateinit var binding: ActivityRaffleBinding
    private val viewModel: MainViewModel by viewModels()

    var category = "기타"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRaffleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        subscribeToObservables()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.btnStartRaffle.setOnClickListener {
            if (!binding.detailSwitch.isChecked) {
                viewModel.makeRandom()
                Log.d("카테고리", "카테고리 = $category ")
            } else {
                viewModel.makeDetailRandom(category)
                Log.d("카테고리", "카테고리 = $category ")
            }
        }
        binding.detailSwitch.setOnCheckedChangeListener() { _, isChecked ->
            if (isChecked) {
                // 체크 되면 보여지게
                binding.categorySpinner.isVisible = isChecked
            } else {
                // 안되면 안보여짐
                binding.categorySpinner.isVisible = false
            }
        }

        binding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    category = p0?.getItemAtPosition(p2).toString()
                    Log.d("카테고리", "onItemSelected: $category")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    category = "기타"
                }

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