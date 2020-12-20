package com.example.chuck_norris.network.exception

import android.content.Context
import com.example.chuck_norris.network.Constants
import com.example.chuck_norris.network.R

class InternalServerErrorException(
    private val context: Context
) : BaseNetworkException(
    Constants.Network.Exceptions.INTERNAL_SERVER_ERROR,
    context.getString(R.string.internal_server_error)
)