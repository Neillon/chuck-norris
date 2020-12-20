package com.example.chuck_norris.network.manager

import android.content.Context
import com.example.chuck_norris.network.Constants
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.network.error_handling.NetworkError
import com.example.chuck_norris.network.error_handling.NetworkErrorFactory
import com.example.chuck_norris.network.exception.BadRequestException
import com.example.chuck_norris.network.exception.GenericNetworkException
import com.example.chuck_norris.network.exception.InternalServerErrorException
import com.example.chuck_norris.network.exception.NoInternetConnectionException
import com.example.chuck_norris.network.manager.status.NetworkStatusManager
import retrofit2.Response
import timber.log.Timber

class NetworkManager(
    private val context: Context,
    private val networkStatusManager: NetworkStatusManager
) {

    /**
     * Auxiliary method to do a request asynchronously using retrofit
     */
    suspend fun <S> doRequestAsync(
        block: suspend () -> Response<S>
    ): Either<S, NetworkError> {

        if (!networkStatusManager.hasInternet())
            return Either.right(NetworkErrorFactory.create(NoInternetConnectionException(context)))

        try {
            val response = block()

            return if (response.isSuccessful) {
                Either.left(
                    response.takeIf { it.isSuccessful }?.body() as S
                )
            } else {
                Either.right(
                    when (response.code()) {
                        Constants.Network.BAD_REQUEST -> NetworkErrorFactory.create(
                            BadRequestException(context)
                        )
                        Constants.Network.INTERNAL_SERVER_ERROR -> NetworkErrorFactory.create(
                            InternalServerErrorException(context)
                        )
                        else -> NetworkErrorFactory.create(GenericNetworkException(context))
                    }
                )
            }

        } catch (e: Exception) {
            Timber.e(e)
            return Either.right(
                NetworkErrorFactory.create(
                    GenericNetworkException(
                        context,
                        error = e.message ?: ""
                    )
                )
            )
        }
    }

}

