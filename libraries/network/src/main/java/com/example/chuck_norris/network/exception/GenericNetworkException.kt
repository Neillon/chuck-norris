package com.example.chuck_norris.network.exception

import android.content.Context
import com.example.chuck_norris.network.R


class GenericNetworkException(
    private val context: Context,
    private var error: String = context.getString(R.string.not_especified_error)
): BaseNetworkException(errorMessage = error)