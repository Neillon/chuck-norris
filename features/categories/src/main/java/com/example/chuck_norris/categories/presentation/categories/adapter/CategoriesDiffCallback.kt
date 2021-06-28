package com.example.chuck_norris.categories.presentation.categories.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.chuck_norris.ui.CategoryUI

object CategoriesDiffCallback : DiffUtil.ItemCallback<CategoryUI>() {
    override fun areItemsTheSame(oldItem: CategoryUI, newItem: CategoryUI) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: CategoryUI, newItem: CategoryUI) =
        oldItem.name == newItem.name
}
