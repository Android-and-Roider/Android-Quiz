package com.example.androidstudyproject

import android.app.Application
import android.content.Context

class App : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
        fun getApplicationContext(): Context {
            return instance.applicationContext
        }
    }

}