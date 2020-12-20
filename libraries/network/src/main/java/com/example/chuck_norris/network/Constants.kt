package com.example.chuck_norris.network

import com.google.gson.GsonBuilder

object Constants {
    object Network {
        object Exceptions {
            const val GENERIC = 0
            const val BAD_REQUEST = 404
            const val INTERNAL_SERVER_ERROR = 500
        }

        object Limits {
            const val CONNECT = 60L
            const val READ = 60L
            const val WRITE = 60L
        }

        internal object GsonDefaults {
            // Api returns date in the format 2020-01-05 13:42:19.576875
            const val apiDataFormatBrazil = "yyyy-MM-dd'T'HH:mm:ss:mm"
            val gsonDefault = GsonBuilder()
                .setDateFormat(apiDataFormatBrazil)
                .create()
        }
    }

    object Permissions {
        const val REQUEST_CODE_INTERNET = 1
    }
}