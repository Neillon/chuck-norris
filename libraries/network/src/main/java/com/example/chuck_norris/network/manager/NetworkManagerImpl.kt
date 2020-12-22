package com.example.chuck_norris.network.manager

import android.content.Context
import com.example.chuck_norris.network.Constants
import com.example.chuck_norris.network.R
import com.example.chuck_norris.network.error_handling.ErrorResponse
import com.example.chuck_norris.network.exception.BadRequestException
import com.example.chuck_norris.network.exception.GenericNetworkException
import com.example.chuck_norris.network.exception.InternalServerErrorException
import com.example.chuck_norris.network.exception.NoInternetConnectionException
import com.example.chuck_norris.network.manager.status.NetworkStatusManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import timber.log.Timber

class NetworkManagerImpl(
    private val context: Context,
    private val networkStatusManager: NetworkStatusManager
): NetworkManager {

    /**
     * Auxiliary method to do a request asynchronously using retrofit
     */
    override suspend fun <S> doAsyncRequest(
        block: suspend () -> Response<S>
    ): S {

        if (!networkStatusManager.hasInternet())
            throw NoInternetConnectionException(context)

        return try {
            val response = block()
            response.takeIf { it.isSuccessful }?.body()
                ?: when (response.code()) {
                    Constants.Network.Exceptions.BAD_REQUEST -> throw BadRequestException(context)
                    // Constants.Network.Exceptions.INTERNAL_SERVER_ERROR -> throw InternalServerErrorException(context)
                    else -> throw GenericNetworkException(deserializeError(response))
                }

        } catch (e: Exception) {
            Timber.e(e)
            throw GenericNetworkException(e.message!!)
        }
    }

    private fun <S> deserializeError(response: Response<S>): String {
        val gson = Gson()
        val type = object : TypeToken<ErrorResponse>() {}.type
        var errorResponse: ErrorResponse? = gson.fromJson(response.errorBody()!!.charStream(), type)

        return errorResponse?.message?.let {
            it.split(":").last()
        } ?: context.getString(R.string.not_especified_error)
    }

}

