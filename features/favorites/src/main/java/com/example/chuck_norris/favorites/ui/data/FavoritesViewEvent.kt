package com.example.chuck_norris.favorites.ui.data

sealed class FavoritesViewEvent {
    object LoadFavoriteJokes: FavoritesViewEvent()
}