package com.example.chuck_norris.jokes.data.mappers

import com.example.chuck_norris.database.entities.JokeEntity
import com.example.chuck_norris.domain.entities.Category
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.jokes.data.response.JokeResponse
import com.example.chuck_norris.ui.JokeUI
import java.time.LocalDate

fun List<String>.toDomain() = this.map { Category(it) }

fun JokeResponse.toDomain() = Joke(
    id = this.id,
    iconUrl = this.iconUrl,
    url = this.url,
    value = this.value,
    categories = this.categories.toDomain()
)

fun Joke.toUI() = JokeUI(
    id = this.id,
    iconUrl = this.iconUrl,
    url = this.url,
    value = this.value
)

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