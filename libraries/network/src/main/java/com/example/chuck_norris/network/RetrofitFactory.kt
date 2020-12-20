package com.example.chuck_norris.network

import com.example.chuck_norris.network.Constants.Network.GsonDefaults.gsonDefault
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import com.example.chuck_norris.network.extensions.OkHttpExtensions.addLogInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    fun <S> createService(
        api: Class<S>
    ): S {
        val client = OkHttpClient.Builder().run {
            connectTimeout(Constants.Network.Limits.CONNECT, TimeUnit.SECONDS)
            readTimeout(Constants.Network.Limits.READ, TimeUnit.SECONDS)
            writeTimeout(Constants.Network.Limits.WRITE, TimeUnit.SECONDS)
            addLogInterceptor()
            build()
        }

        return Retrofit.Builder().run {
            client(client)
            addConverterFactory(GsonConverterFactory.create(gsonDefault))
            baseUrl(BuildConfig.BASE_URL)
            build()
        }.create(api)
    }

}

