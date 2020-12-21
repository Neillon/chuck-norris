package com.example.chuck_norris.categories.di

import com.example.chuck_norris.categories.data.api.CategoriesApi
import com.example.chuck_norris.categories.data.repository.CategoriesRemoteRepository
import com.example.chuck_norris.categories.data.repository.CategoriesRepository
import com.example.chuck_norris.categories.domain.usecase.GetCategoriesUseCase
import com.example.chuck_norris.categories.ui.CategoriesViewModel
import com.example.chuck_norris.network.RetrofitFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CategoriesModule {
    val dependencies = module {

        // Api
        single { RetrofitFactory.createService(CategoriesApi::class.java) }

        // Repository
        factory<CategoriesRepository> {
            CategoriesRemoteRepository(
                categoriesApi = get(),
                networkManager = get()
            )
        }

        // UseCase
        factory { GetCategoriesUseCase(repository = get()) }

        // ViewModel
        viewModel { CategoriesViewModel(getCategoriesUseCase = get()) }
    }
}