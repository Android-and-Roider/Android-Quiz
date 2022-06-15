package com.example.androidstudyproject.data

import androidx.lifecycle.LiveData

class MainRepository(mDatabase: FoodDatabase) {

    private val dao = mDatabase.foodDao()
    val allFood: LiveData<List<Food>> = dao.getAll()

    companion object {
        private var sInstance: MainRepository? = null
        fun getInstance(database: FoodDatabase): MainRepository {
            return sInstance
                ?: synchronized(this) {
                    val instance = MainRepository(database)
                    sInstance = instance
                    instance
                }
        }
    }

    fun insert(food: Food) {
        dao.insert(food)
    }

    fun delete(food: Food) {
        dao.delete(food)
    }

    /*fun selectByCategory(category: String): LiveData<List<Food>>? {
        return dao.selectByCategory(category)
    }

    fun selectByMeat(meat: Boolean): LiveData<List<Food>>? {
        return dao.selectByMeat(meat)
    }

    fun selectByFruit(fruit: String): LiveData<List<Food>>? {
        return dao.selectByCategory(fruit)
    }
*/
}