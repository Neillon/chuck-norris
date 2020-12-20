package com.example.chuck_norris.network.exception

import android.content.Context
import com.example.chuck_norris.network.R

class NoInternetConnectionException(
    private val context: Context
) : BaseNetworkException(
    errorMessage = context.getString(R.string.no_internet_connection)
)