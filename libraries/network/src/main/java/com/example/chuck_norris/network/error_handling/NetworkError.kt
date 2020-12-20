package com.example.chuck_norris.network.error_handling

data class NetworkError(
    val message: String,
    val status: Int,
    val exception: Throwable
)