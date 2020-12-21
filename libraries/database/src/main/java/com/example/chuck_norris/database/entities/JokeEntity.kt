package com.example.chuck_norris.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "jokes"
)
data class JokeEntity(
    @PrimaryKey
    val id: Long? = null,

    @ColumnInfo(name = "remote_id") val remoteId: String,

    @ColumnInfo(name = "url") val url: String,

    @ColumnInfo(name = "value") val value: String,

    @ColumnInfo(name = "iconUrl") val iconUrl: String
)
