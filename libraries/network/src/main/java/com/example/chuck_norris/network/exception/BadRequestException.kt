package com.example.chuck_norris.network.exception

import com.example.chuck_norris.network.Constants

class BadRequestException : BaseNetworkException(
    Constants.Network.Exceptions.BAD_REQUEST,
    Constants.Network.Exceptions.Messages.BAD_REQUEST
)