package com.example.chuck_norris.database.di

import com.example.chuck_norris.database.ChuckNorrisDatabase
import org.koin.dsl.module

object DatabaseModule {
    val dependencies = module {
        // Database
        single { ChuckNorrisDatabase.create(get()) }
    }
}