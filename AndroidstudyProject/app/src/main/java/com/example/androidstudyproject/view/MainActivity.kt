package com.example.androidstudyproject.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidstudyproject.MainViewModel
import com.example.androidstudyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMenuList.setOnClickListener {
            // 메뉴 목록 확인&추가&삭제
        }
        binding.btnStartChoose.setOnClickListener {
            // 메뉴 추천 시작
        }
    }
}