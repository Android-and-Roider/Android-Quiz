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

    @Query("SELECT * FROM Food ORDER BY RANDOM() LIMIT 1")
    fun getRandom(): Food

    /*  @Query("SELECT * FROM food WHERE is_fruit = :b AND is_dairy_product  ")
      fun tmp(a: String, b: Boolean, c: Boolean)*/
    // Todo<알아서 해라>

}