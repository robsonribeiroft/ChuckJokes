package com.robsonribeiroft.chuckjokes.feature.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.robsonribeiroft.chuckjokes.databinding.AdapterItemCategoryBinding

class CategoriesAdapter(
    var onItemClicked: (String) -> Unit = {}
): ListAdapter<String, CategoriesAdapter.ViewHolder>(CategoriesAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }

    inner class ViewHolder(
        private val binding: AdapterItemCategoryBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClicked(getItem(adapterPosition))
            }
        }

        fun bind(item: String) {
            binding.textCategory.text = item
        }
    }
}
