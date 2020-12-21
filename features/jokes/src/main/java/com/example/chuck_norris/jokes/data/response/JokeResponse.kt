package com.example.chuck_norris.jokes.data.response

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("id") val id: String,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("value") val value: String,
    @SerializedName("categories") val categories: List<String> = emptyList(),
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)
