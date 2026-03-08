package com.sorianog.moovees.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sorianog.moovees.data.entity.MovieModelLocal

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getMovies(): List<MovieModelLocal>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id: Int): MovieModelLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieModelLocal>)

    @Update
    suspend fun updateMovie(movie: MovieModelLocal)

    @Query("UPDATE movie SET marked = :marked, markedOn = :markedOn WHERE id = :id")
    fun updateMovie(id: Int, marked: Boolean, markedOn: String)

    @Query("DELETE FROM movie")
    fun deleteAllMovies()
}