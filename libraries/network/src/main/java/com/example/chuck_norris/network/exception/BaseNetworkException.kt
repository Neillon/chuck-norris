package com.example.chuck_norris.network.exception

import com.example.chuck_norris.network.Constants

abstract class BaseNetworkException(
    var statusCode: Int = Constants.Network.GENERIC,
    var errorMessage: String = ""
): Exception(errorMessage) {

}