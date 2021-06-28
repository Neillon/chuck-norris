package com.example.chuck_norris.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryUI(
    val name: String
) : Parcelable