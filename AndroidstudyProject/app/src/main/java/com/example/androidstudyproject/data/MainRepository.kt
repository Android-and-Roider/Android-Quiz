package com.example.androidstudyproject.data

import com.example.androidstudyproject.App

class MainRepository() {
    val db = FoodDatabase.getInstance(App.getApplicationContext())
}