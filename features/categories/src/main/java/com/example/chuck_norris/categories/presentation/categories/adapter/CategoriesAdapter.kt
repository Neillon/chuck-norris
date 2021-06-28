package com.example.chuck_norris.categories.presentation.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck_norris.categories.R
import com.example.chuck_norris.categories.databinding.ItemCategoryBinding
import com.example.chuck_norris.ui.CategoryUI
import timber.log.Timber

class CategoriesAdapter(
    private val categoryClick: CategoriesItemClick
) :
    ListAdapter<CategoryUI, CategoriesAdapter.CategoriesViewHolder>(CategoriesDiffCallback) {

    private lateinit var binding: ItemCategoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context))
        return CategoriesViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    /**
     * Set data into the categories adapter list
     */
    fun insertData(values: List<CategoryUI>) {
        submitList(values)
    }

    inner class CategoriesViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
        private val imageButtonShowDetails =
            itemView.findViewById<ImageView>(R.id.imageButtonShowDetails)

        /**
         * bind the data into the ViewHolder/List
         */
        fun bind(category: CategoryUI) {
            textViewName.text = category.name

            itemView.setOnClickListener { doClick(category) }
            textViewName.setOnClickListener { doClick(category) }
            imageButtonShowDetails.setOnClickListener { doClick(category) }
        }

        private fun doClick(category: CategoryUI) {
            Timber.i("Clicked on category $category.name")
            categoryClick.onCategoryItemClick(category)
        }
    }

}