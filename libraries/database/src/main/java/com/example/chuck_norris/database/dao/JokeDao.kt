package com.example.chuck_norris.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chuck_norris.database.entities.JokeEntity

@Dao
interface JokeDao {

    @Query("SELECT * FROM jokes")
    suspend fun getFavoriteJokes(): List<JokeEntity>

    @Query("SELECT * FROM jokes WHERE remote_id = :remoteId limit 1")
    suspend fun findByRemoteId(remoteId: String): JokeEntity?

    @Query("SELECT * FROM jokes WHERE id = :id")
    suspend fun findById(id: Long): JokeEntity

    @Insert
    suspend fun create(joke: JokeEntity): Long

}