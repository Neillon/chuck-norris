package com.example.chuck_norris.network

import com.example.chuck_norris.network.manager.NetworkManager
import com.example.chuck_norris.network.manager.NetworkManagerImpl
import com.example.chuck_norris.network.manager.status.NetworkStatusManager
import com.example.chuck_norris.network.manager.status.NetworkStatusManagerImpl
import org.koin.dsl.module

object NetworkModule {
    val dependencies = module {
        single<NetworkStatusManager> { NetworkStatusManagerImpl(context = get()) }
        single<NetworkManager> { NetworkManagerImpl(context = get(), networkStatusManager = get()) }
    }
}