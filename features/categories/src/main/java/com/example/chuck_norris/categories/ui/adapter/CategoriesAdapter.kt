package com.example.chuck_norris.categories.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck_norris.categories.R
import com.example.chuck_norris.categories.databinding.ItemCategoryBinding
import com.example.chuck_norris.categories.ui.data.CategoryUI
import timber.log.Timber

class CategoriesDiffCallback : DiffUtil.ItemCallback<CategoryUI>() {
    override fun areItemsTheSame(oldItem: CategoryUI, newItem: CategoryUI) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: CategoryUI, newItem: CategoryUI) =
        oldItem.name == newItem.name
}

class CategoriesAdapter :
    ListAdapter<CategoryUI, CategoriesAdapter.CategoriesViewHolder>(CategoriesDiffCallback()) {

    private lateinit var binding: ItemCategoryBinding
    private var categories = mutableListOf<CategoryUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context))
        return CategoriesViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size

    /**
     * Set data into the categories adapter list
     */
    fun insertData(values: List<CategoryUI>) {
        categories.addAll(values)
        notifyDataSetChanged()
    }

    inner class CategoriesViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
        private val imageButtonShowDetails = itemView.findViewById<ImageView>(R.id.imageButtonShowDetails)

        /**
         * bind the data into the ViewHolder/List
         */
        fun bind(category: CategoryUI) {
            textViewName.text = category.name
            imageButtonShowDetails.setOnClickListener { Timber.d("All good!") }
        }
    }

}