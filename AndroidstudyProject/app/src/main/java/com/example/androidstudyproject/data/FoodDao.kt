package com.example.androidstudyproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {
    @Insert
    fun insert(food: Food)

    @Query("DELETE FROM Food WHERE name = :food")
    fun delete(food: Food)

    @Query("SELECT * FROM Food WHERE category = :category")
    fun selectByCategory(category: String): MutableList<Food>?

    @Query("SELECT * FROM Food WHERE is_meat = :meat")
    fun selectByMeat(meat: Boolean): MutableList<Food>?

    @Query("SELECT * FROM Food WHERE is_fruit = :fruit")
    fun selectByFruit(fruit: Boolean): MutableList<Food>?

    @Query("SELECT * FROM Food WHERE is_dairy_product = :diary")
    fun selectByDairy(diary: Boolean): MutableList<Food>?

}