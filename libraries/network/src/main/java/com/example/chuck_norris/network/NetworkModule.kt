package com.example.chuck_norris.network

import org.koin.dsl.module

object NetworkModule {
    val dependencies = module {
//        single<NetworkStatusManager> { NetworkStatusManagerImpl(context = get()) }
//        single<NetworkManager> { NetworkManagerImpl(context = get(), networkStatusManager = get()) }
    }
}