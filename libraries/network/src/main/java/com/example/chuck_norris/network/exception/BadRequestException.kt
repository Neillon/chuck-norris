package com.example.chuck_norris.network.exception

import android.content.Context
import com.example.chuck_norris.network.Constants
import com.example.chuck_norris.network.R

class BadRequestException(
    private val context: Context,
) : BaseNetworkException(
    Constants.Network.BAD_REQUEST,
    context.getString(R.string.bad_request_exception_message)
)