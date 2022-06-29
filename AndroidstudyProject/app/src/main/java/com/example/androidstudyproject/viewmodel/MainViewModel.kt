package com.example.androidstudyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _selectedFood = MutableLiveData<Food>()
    val selectedFood: LiveData<Food>
        get() = _selectedFood

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun insertFood(food: Food) = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.insert(food)
    }

    fun deleteFood(food: Food) = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.delete(food)
    }

    fun makeRandom() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            runCatching {
                _selectedFood.postValue(mainRepository.select())
            }.onSuccess {
                _isLoading.postValue(false)
            }.onFailure {
                _isLoading.postValue(false)
            }
        }
    }

    fun makeDetailRandom(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            runCatching {
                _selectedFood.postValue(mainRepository.selectDetail(category))
            }.onSuccess {
                _isLoading.postValue(false)
            }.onFailure {
                _isLoading.postValue(false)
            }
        }
    }

}