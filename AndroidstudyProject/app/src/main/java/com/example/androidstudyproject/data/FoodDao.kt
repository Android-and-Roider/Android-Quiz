package com.example.androidstudyproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("SELECT * FROM Food")
    fun getAll(): LiveData<List<Food>>

    @Insert
    fun insert(food: Food)

    @Delete
    fun delete(food: Food)

//    @Query("SELECT * FROM Food WHERE category = :category")
//    fun selectByCategory(category: String): MutableLiveData<List<Food>>?
//
//    @Query("SELECT * FROM Food WHERE is_meat = :meat")
//    fun selectByMeat(meat: Boolean): MutableLiveData<List<Food>>?
//
//    @Query("SELECT * FROM Food WHERE is_fruit = :fruit")
//    fun selectByFruit(fruit: Boolean): MutableLiveData<List<Food>>?
//
//    @Query("SELECT * FROM Food WHERE is_dairy_product = :diary")
//    fun selectByDairy(diary: Boolean): MutableLiveData<List<Food>>?

}