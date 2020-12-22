package com.example.chuck_norris.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object GsonUtil {

    val gsonDefault: Gson = GsonBuilder()
        .setDateFormat(Constants.Network.GsonDefaults.apiDataFormatBrazil)
        .create()

    inline fun <reified T> fromJson(json: String): T {
        return gsonDefault.fromJson(json, object: TypeToken<T>() { }.type)
    }
}