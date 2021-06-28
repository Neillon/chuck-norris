package com.example.chuck_norris.network.manager

import com.example.chuck_norris.network.Constants
import com.example.chuck_norris.network.error_handling.ErrorResponse
import com.example.chuck_norris.network.exception.BadRequestException
import com.example.chuck_norris.network.exception.GenericNetworkException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import timber.log.Timber

object NetworkManager {

    /**
     * Auxiliary method to do a request asynchronously using retrofit
     */
    suspend fun <S> doAsyncRequest(
        block: suspend () -> Response<S>
    ): S {
        return try {
            val response = block()
            response.takeIf { it.isSuccessful }?.body()
                ?: when (response.code()) {
                    Constants.Network.Exceptions.BAD_REQUEST -> throw BadRequestException()
                    else -> throw GenericNetworkException(deserializeError(response)) // Why ChuckNorrisApi returns the error in status code 500???
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
        } ?: Constants.Network.Exceptions.Messages.NOT_SPECIFIED_ERROR
    }

}

