package com.example.chuck_norris.network.manager.status

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Auxiliary class to verify the phone internet status
 */
class NetworkStatusManagerImpl(
    private val context: Context
): NetworkStatusManager {

    /**
     * Verify if has internet in order to do a request
     */
    override fun hasInternet(): Boolean {
        val hasInternet = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val connectivityManager = context.getSystemService(ConnectivityManager::class.java)

            val currentNetwork = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(currentNetwork)

            if (capabilities != null)
                capabilities!!.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            else
                false
        } else {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.activeNetworkInfo != null &&
                    connectivityManager.activeNetworkInfo!!.isConnected
        }

        return hasInternet
    }
}