package com.example.androidstudyproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    var name: String, // 메뉴 이름
    var category: String, // 일식, 양식, 등등...
    var is_meat: Boolean, // 채식, 육식
    var is_fruit: Boolean, // 과일 여부
    var is_dairy_product: Boolean // 유제품 여부
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
