package com.example.androidstudyproject.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModel
import com.example.androidstudyproject.databinding.DialogAddBinding

class AddDialog constructor(context: Context) : Dialog(context) {
    val binding: DialogAddBinding = DialogAddBinding.inflate(layoutInflater)

    init {
        setCanceledOnTouchOutside(true)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
    }

}