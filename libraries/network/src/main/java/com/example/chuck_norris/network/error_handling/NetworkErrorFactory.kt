package com.example.chuck_norris.network.error_handling

import com.example.chuck_norris.network.exception.BaseNetworkException

object NetworkErrorFactory {
    fun create(exceptionBase: BaseNetworkException): NetworkError = NetworkError(
        exceptionBase.message ?: "",
        exceptionBase.statusCode,
        exceptionBase
    )
}