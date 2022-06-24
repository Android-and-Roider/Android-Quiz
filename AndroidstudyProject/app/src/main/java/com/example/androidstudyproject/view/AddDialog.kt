package com.example.androidstudyproject.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.androidstudyproject.R
import com.example.androidstudyproject.databinding.DialogAddBinding

class AddDialog(context: Context) : Dialog(context), AdapterView.OnItemSelectedListener {
    private val binding: DialogAddBinding = DialogAddBinding.inflate(layoutInflater)
    private lateinit var onClickedListener: ButtonClickListener
    var isMeat = false
    var isDiary = false
    var isFruit = false
    var category = "기타"

    init {
        setCanceledOnTouchOutside(true)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)

        ArrayAdapter.createFromResource(
            context,
            R.array.category,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.category.adapter = adapter
        }
        binding.category.onItemSelectedListener = this
    }

    override fun show() {
        binding.cancel.setOnClickListener {
            dismiss()
        }
        binding.btnAdd.setOnClickListener {
            check()
            onClickedListener.onClicked(
                binding.addName.text.toString(),
                isMeat,
                isFruit,
                isDiary,
                category
            )
            dismiss()
        }

        super.show()
    }

    override fun dismiss() {
        binding.apply {
            this.addName.text.clear()
            this.isDairy.isChecked = false
            this.isFruit.isChecked = false
            this.isMeat.isChecked = false
        }
        super.dismiss()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        category = parent?.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        category = "기타"
    }

    private fun check() {
        if (binding.isFruit.isChecked) {
            isFruit = true
        }
        if (binding.isMeat.isChecked) {
            isMeat = true
        }
        if (binding.isDairy.isChecked) {
            isDiary = true
        }
    }

    interface ButtonClickListener {
        fun onClicked(name: String, meat: Boolean, fruit: Boolean, dairy: Boolean, category: String)
    }

    fun setOnClickListener(listener: ButtonClickListener) {
        onClickedListener = listener
    }
}