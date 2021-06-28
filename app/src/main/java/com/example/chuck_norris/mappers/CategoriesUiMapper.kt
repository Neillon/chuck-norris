package com.example.chuck_norris.mappers

import com.example.chuck_norris.entities.Category
import com.example.chuck_norris.ui.CategoryUI

fun Category.toUI() = CategoryUI(this.name)