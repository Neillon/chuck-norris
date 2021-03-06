package com.example.chuck_norris.favorites.data.mappers

import com.example.chuck_norris.entities.Joke
import com.example.chuck_norris.ui.JokeUI
import com.example.chuck_norris.database.entities.JokeEntity

fun List<Joke>.toUI() = this.map {
    JokeUI(
        id = it.id,
        value = it.value,
        iconUrl = it.iconUrl,
        url = it.url,
        isFavorite = true
    )
}

fun List<JokeEntity>.toDomain() = this.map {
    Joke(
        id = it.remoteId,
        iconUrl = it.iconUrl,
        url = it.url,
        value = it.value,
        categories = emptyList()
    )
}