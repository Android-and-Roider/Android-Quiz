package com.example.androidstudyproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidstudyproject.App
import com.example.androidstudyproject.data.Food
import com.example.androidstudyproject.data.FoodDatabase
import com.example.androidstudyproject.data.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mainRepository =
        MainRepository(FoodDatabase.getInstance(App.instance, viewModelScope))

    var food = mainRepository.allFood

    fun insertFood(food: Food) = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.insert(food)
    }

    fun deleteFood(food: Food) = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.delete(food)
    }


//    fun selectByCategory(category: String): LiveData<List<Food>> {
//        viewModelScope.launch(Dispatchers.IO) {
//            food = selectByCategory(category)
//        }
//    }
//
//    fun selectByMeat(meat: Boolean): LiveData<List<Food>>? {
//        viewModelScope.launch(Dispatchers.IO) {
//            _food = mainRepository.db!!.foodDao().selectByMeat(meat)
//        }
//        return food
//    }
//
//    fun selectByFruit(fruit: Boolean): LiveData<List<Food>>? {
//        viewModelScope.launch(Dispatchers.IO) {
//            _food = mainRepository.db!!.foodDao().selectByFruit(fruit)
//        }
//        return food
//    }
//
//    fun selectByDiary(diary: Boolean): LiveData<List<Food>>? {
//        CoroutineScope(Dispatchers.IO).launch {
//            _food = mainRepository.db!!.foodDao().selectByDairy(diary)
//        }
//        return food
//    }

}