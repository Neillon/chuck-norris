package com.example.chuck_norris.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Joke(
    val id: String,
    val iconUrl: String,
    val url: String,
    val value: String,
    val categories: @RawValue List<Category> = emptyList()
) : Parcelable