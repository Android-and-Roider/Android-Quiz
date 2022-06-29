package com.example.androidstudyproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    var name: String, // 메뉴 이름
    var category: String, // 일식, 양식, 등등...
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
