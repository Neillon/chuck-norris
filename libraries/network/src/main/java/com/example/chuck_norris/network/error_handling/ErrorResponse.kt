package com.example.chuck_norris.network.error_handling

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("status") val status: Int,
    @SerializedName("error") val error: String,
    @SerializedName("message") val message: String,
    @SerializedName("violations") val violations: Pair<String, String>
)
