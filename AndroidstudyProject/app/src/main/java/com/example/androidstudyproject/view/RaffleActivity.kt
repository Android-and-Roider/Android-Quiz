package com.example.androidstudyproject.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.androidstudyproject.databinding.ActivityRaffleBinding
import com.example.androidstudyproject.viewmodel.MainViewModel

class RaffleActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    lateinit var binding: ActivityRaffleBinding
    private val viewModel: MainViewModel by viewModels()

    var meat = false
    var diary = false
    var fruit = false
    var category = "기타"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRaffleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        subscribeToObservables()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.meatBox.setOnCheckedChangeListener { compoundButton, b ->
            meat = if (b) {
                b
            } else false
        }
        binding.diaryBox.setOnCheckedChangeListener { compoundButton, b ->
            meat = if (b) {
                b
            } else false
        }
        binding.fruitBox.setOnCheckedChangeListener { compoundButton, b ->
            meat = if (b) {
                b
            } else false
        }

        binding.btnStartRaffle.setOnClickListener {
            if (!binding.detailSwitch.isChecked) {
                viewModel.makeRandom()
            }else{
                viewModel.makeDetailRandom(category, meat, fruit, diary)
            }
        }
        binding.detailSwitch.setOnCheckedChangeListener() { _, isChecked ->
            if (isChecked) {
                // 체크 되면 보여지게
                binding.diaryBox.isVisible = isChecked
                binding.fruitBox.isVisible = isChecked
                binding.meatBox.isVisible = isChecked
                binding.categorySpinner.isVisible = isChecked
            } else {
                // 안되면 안보여짐
                binding.diaryBox.isVisible = false
                binding.fruitBox.isVisible = false
                binding.meatBox.isVisible = false
                binding.categorySpinner.isVisible = false
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        category = p0?.getItemAtPosition(p2).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        category = "기타"
    }

}