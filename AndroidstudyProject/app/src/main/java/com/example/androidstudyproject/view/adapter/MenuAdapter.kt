package com.example.androidstudyproject.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyproject.data.Food
import com.example.androidstudyproject.databinding.ItemMenuBinding

class MenuAdapter(private val itemClickListener: (Food) -> Unit) :
    ListAdapter<Food, MenuAdapter.ViewHolder>(menuDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemMenuBinding,
        private val itemClickListener: (Food) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                binding.food!!.run {
                    itemClickListener(this)
                }
            }
        }

        fun bind(food: Food) {
            binding.food = food
            binding.executePendingBindings()
        }
    }

    companion object {
        private val menuDiffUtil = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem == newItem

        }
    }

}