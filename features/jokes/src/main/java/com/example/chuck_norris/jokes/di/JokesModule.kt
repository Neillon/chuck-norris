package com.example.chuck_norris.jokes.di

import com.example.chuck_norris.jokes.data.api.JokeApi
import com.example.chuck_norris.jokes.data.repository.JokeLocalRepository
import com.example.chuck_norris.jokes.data.repository.JokeLocalRepositoryImpl
import com.example.chuck_norris.jokes.data.repository.JokeRemoteRepositoryImpl
import com.example.chuck_norris.jokes.data.repository.JokeRemoteRepository
import com.example.chuck_norris.jokes.domain.usecase.FavoriteJokeUseCase
import com.example.chuck_norris.jokes.domain.usecase.FindJokeByRemoteIdUseCase
import com.example.chuck_norris.jokes.domain.usecase.GetRandomJokeByCategoryUseCase
import com.example.chuck_norris.jokes.domain.usecase.SearchJokeByDescriptionUseCase
import com.example.chuck_norris.jokes.ui.detail.JokeDetailViewModel
import com.example.chuck_norris.jokes.ui.search.SearchJokesViewModel
import com.example.chuck_norris.network.RetrofitFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object JokesModule {
    val dependencies = module {

        //Api
        single { RetrofitFactory.createService(JokeApi::class.java) }

        // Repository
        single<JokeRemoteRepository> { JokeRemoteRepositoryImpl(get()) }
        single<JokeLocalRepository> { JokeLocalRepositoryImpl(get(), get()) }

        // UseCase
        factory { GetRandomJokeByCategoryUseCase(get()) }
        factory { FindJokeByRemoteIdUseCase(get()) }
        factory { FavoriteJokeUseCase(get(), get()) }
        factory { SearchJokeByDescriptionUseCase(get()) }

        // ViewModel
        viewModel { JokeDetailViewModel(get(), get(), get()) }
        viewModel { SearchJokesViewModel(get()) }

    }
}