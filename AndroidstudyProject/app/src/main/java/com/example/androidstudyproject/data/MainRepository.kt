package com.example.androidstudyproject.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.delay

class MainRepository(mDatabase: FoodDatabase) {

    private val dao = mDatabase.foodDao()

    var allFood: LiveData<List<Food>> = dao.getAll()

    fun insert(food: Food) {
        dao.insert(food)
    }

    fun delete(food: Food) {
        dao.delete(food)
    }

    suspend fun select(): Food {
        delay(2500L)
        return dao.getRandom()
    }

    suspend fun selectDetail(category: String) : Food {
        delay(2500L)
        return dao.getDetailRandom(category)
    }

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

}