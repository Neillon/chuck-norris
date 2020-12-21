package com.example.chuck_norris.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JokeUI(
    val id: String,
    val iconUrl: String,
    val url: String,
    val value: String,
    val isFavorite: Boolean = false
) : Parcelable