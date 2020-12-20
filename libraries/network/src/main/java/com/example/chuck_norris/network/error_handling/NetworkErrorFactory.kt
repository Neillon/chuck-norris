package com.example.chuck_norris.network.error_handling

object NetworkErrorFactory {
    fun create(statusCode: Int, exception: Throwable): NetworkError = NetworkError(
        exception.localizedMessage,
        statusCode,
        exception
    )
}