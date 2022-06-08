package com.example.androidstudyproject

import androidx.lifecycle.ViewModel
import com.example.androidstudyproject.data.Food
import com.example.androidstudyproject.data.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    private var _food: List<Food>? = null
    val food: List<Food>?
        get() = _food

    fun insertFood(food: Food) {
        CoroutineScope(Dispatchers.IO).launch {
            mainRepository.db!!.foodDao().insert(food)
        }
    }

    fun deleteFood(food: Food) {
        CoroutineScope(Dispatchers.IO).launch {
            mainRepository.db!!.foodDao().delete(food)
        }
    }

    fun selectByCategory(category: String): List<Food>? {
        CoroutineScope(Dispatchers.IO).launch {
            _food = mainRepository.db!!.foodDao().selectByCategory(category)
        }
        return food
    }

    fun selectByMeat(meat:Boolean): List<Food>? {
        CoroutineScope(Dispatchers.IO).launch {
            _food = mainRepository.db!!.foodDao().selectByMeat(meat)
        }
        return food
    }

    fun selectByFruit(fruit:Boolean): List<Food>? {
        CoroutineScope(Dispatchers.IO).launch {
            _food = mainRepository.db!!.foodDao().selectByFruit(fruit)
        }
        return food
    }

    fun selectByDiary(diary:Boolean): List<Food>? {
        CoroutineScope(Dispatchers.IO).launch {
            _food = mainRepository.db!!.foodDao().selectByDairy(diary)
        }
        return food
    }

}