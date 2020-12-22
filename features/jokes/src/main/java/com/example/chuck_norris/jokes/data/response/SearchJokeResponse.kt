package com.example.chuck_norris.jokes.data.response

import com.google.gson.annotations.SerializedName

data class SearchJokeResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("result") val result: List<JokeResponse>
)