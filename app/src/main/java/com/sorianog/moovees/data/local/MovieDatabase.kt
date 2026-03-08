package com.sorianog.moovees.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sorianog.moovees.data.entity.MovieModelLocal

@Database(entities = [MovieModelLocal::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}