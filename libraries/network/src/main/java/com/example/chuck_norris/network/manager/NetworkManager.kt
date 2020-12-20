package com.example.chuck_norris.network.manager

import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.network.error_handling.NetworkError
import retrofit2.Response

interface NetworkManager {

    suspend fun <S> doAsyncRequest(block: suspend () -> Response<S>): S

}