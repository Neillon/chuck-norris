package com.example.chuck_norris.jokes.di

import com.example.chuck_norris.jokes.ui.detail.JokeDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object JokesModule {
    val dependencies = module {

        // ViewModel
        viewModel { JokeDetailViewModel() }

    }
}