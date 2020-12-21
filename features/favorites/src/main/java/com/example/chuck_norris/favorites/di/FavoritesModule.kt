package com.example.chuck_norris.favorites.di

import com.example.chuck_norris.favorites.data.repository.JokesLocalRepository
import com.example.chuck_norris.favorites.data.repository.JokesLocalRepositoryImpl
import com.example.chuck_norris.favorites.domain.usecase.GetFavoriteJokesUseCase
import com.example.chuck_norris.favorites.ui.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FavoritesModule {
    val dependencies = module {
        // Repository
        single<JokesLocalRepository> { JokesLocalRepositoryImpl(get()) }

        // UseCase
        factory { GetFavoriteJokesUseCase(get()) }

        // ViewModel
        viewModel { FavoritesViewModel(get()) }
    }
}