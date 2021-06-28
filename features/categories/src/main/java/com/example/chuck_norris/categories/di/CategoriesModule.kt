package com.example.chuck_norris.categories.di

import com.example.chuck_norris.categories.data.api.CategoriesApi
import com.example.chuck_norris.categories.data.repository.CategoriesRepositoryImpl
import com.example.chuck_norris.categories.domain.abstractions.CategoriesRepository
import com.example.chuck_norris.categories.domain.usecase.GetCategoriesUseCaseImpl
import com.example.chuck_norris.categories.presentation.categories.CategoriesViewModel
import com.example.chuck_norris.network.RetrofitFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object CategoriesModule {
    val dependencies = module {

        // Api
        single { RetrofitFactory.createService<CategoriesApi>() }

        // Repository
        factory { CategoriesRepositoryImpl(get(), get()) } bind CategoriesRepository::class

        // UseCase
        factory { GetCategoriesUseCaseImpl(repository = get()) }

        // ViewModel
        viewModel { CategoriesViewModel(getCategoriesUseCase = get()) }
    }
}