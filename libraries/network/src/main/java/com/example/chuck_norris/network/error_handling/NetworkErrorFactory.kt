package com.example.chuck_norris.network.error_handling

import com.example.chuck_norris.network.exception.BaseNetworkException

object NetworkErrorFactory {
    fun create(exception: BaseNetworkException): NetworkError = NetworkError(
        exception.message ?: "",
        exception.statusCode,
        exception
    )
}