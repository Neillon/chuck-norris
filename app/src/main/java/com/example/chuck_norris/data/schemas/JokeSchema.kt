package com.example.chuck_norris.data.schemas

import com.example.chuck_norris.model.Category
import com.example.chuck_norris.model.Joke
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class JokeSchema(
    @SerializedName("id") val id: String,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("value") val value: String,
    @SerializedName("categories") val categories: List<String> = emptyList(),
    @SerializedName("created_at") val createdAt: LocalDate,
    @SerializedName("updated_at") val updatedAt: LocalDate
) {
    fun toDomain() = Joke(
        id = this.id,
        iconUrl = this.iconUrl,
        url = this.url,
        value = this.value,
        categories = this.categories.toDomain()
    )
}

private fun List<String>.toDomain(): List<Category> = this.map { Category(it) }

