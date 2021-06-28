package com.example.chuck_norris.network

import com.example.chuck_norris.network.Constants.Network.GsonDefaults.gsonDefault
import com.example.chuck_norris.network.extensions.OkHttpExtensions.addLogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    inline fun <reified S> createService(): S {
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
        }.create(S::class.java)
    }

}

