package com.example.chuck_norris.network.exception

class GenericNetworkException(
    private var error: String = ""
): BaseNetworkException(errorMessage = error)