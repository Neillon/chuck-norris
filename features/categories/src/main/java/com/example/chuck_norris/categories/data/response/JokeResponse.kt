package com.example.chuck_norris.categories.data.response

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class JokeResponse(
    @SerializedName("id") val id: String,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("value") val value: String,
    @SerializedName("categories") val categories: List<String> = emptyList(),
    @SerializedName("created_at") val createdAt: LocalDate,
    @SerializedName("updated_at") val updatedAt: LocalDate
)