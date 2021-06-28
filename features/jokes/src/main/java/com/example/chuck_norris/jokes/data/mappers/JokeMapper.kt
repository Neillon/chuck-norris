package com.example.chuck_norris.jokes.data.mappers

import com.example.chuck_norris.entities.Category
import com.example.chuck_norris.entities.Joke
import com.example.chuck_norris.ui.JokeUI
import com.example.chuck_norris.database.entities.JokeEntity
import com.example.chuck_norris.jokes.data.response.JokeResponse

fun List<String>.toDomain() = this.map { Category(it) }

fun JokeResponse.toDomain() = Joke(
    id = this.id,
    iconUrl = this.iconUrl,
    url = this.url,
    value = this.value,
    categories = this.categories.toDomain()
)

fun List<JokeResponse>.toJokeList() = this.map { it.toDomain() }

fun Joke.toUI() = JokeUI(
    id = this.id,
    iconUrl = this.iconUrl,
    url = this.url,
    value = this.value
)

fun List<Joke>.toUI(favorite: Boolean = true) =
    this.map { it.toUI().apply { isFavorite = favorite } }

fun JokeUI.toDomain() = Joke(
    id = this.id,
    iconUrl = this.iconUrl,
    url = this.url,
    value = this.value,
    categories = emptyList()
)

fun Joke.toEntity() = JokeEntity(
    remoteId = this.id,
    url = this.url,
    value = this.value,
    iconUrl = this.iconUrl
)

fun JokeEntity.toDomain() = Joke(
    id = this.remoteId,
    url = this.url,
    value = this.value,
    iconUrl = this.iconUrl
)