package com.example.chuck_norris.network.exception

import com.example.chuck_norris.network.Constants

abstract class BaseNetworkException(
    var statusCode: Int = Constants.Network.Exceptions.GENERIC,
    var errorMessage: String = ""
): Exception(errorMessage) {

}