package com.example.androidstudyproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope


@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null
        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): FoodDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}