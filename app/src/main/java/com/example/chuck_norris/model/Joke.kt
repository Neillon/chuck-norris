package com.example.chuck_norris.model

data class Joke(
    val id: String,
    val iconUrl: String,
    val url: String,
    val value: String,
    val categories: List<Category> = emptyList()
)