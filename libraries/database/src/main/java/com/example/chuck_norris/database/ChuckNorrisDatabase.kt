package com.example.chuck_norris.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chuck_norris.database.dao.JokeDao
import com.example.chuck_norris.database.entities.JokeEntity

@Database(
    entities = [JokeEntity::class],
    version = Constants.DB_VERSION
)
abstract class ChuckNorrisDatabase : RoomDatabase() {

    abstract fun jokeDao(): JokeDao

    companion object {
        private val instance: ChuckNorrisDatabase? = null

        /**
         * Create a new instance of database
         */
        fun create(context: Context): ChuckNorrisDatabase {
            return instance ?: Room.databaseBuilder(
                context,
                ChuckNorrisDatabase::class.java,
                "chuck_norris"
            ).build()
        }
    }

}