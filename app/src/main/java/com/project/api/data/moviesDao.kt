package com.project.api.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface moviesDao {
    @Query("SELECT * FROM movies WHERE page = :page ")
     suspend fun getMoviesByPage(page: Int): List<Movie>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertMovies(movies: List<Movie>)

    @Transaction
    @Query("DELETE FROM movies WHERE page NOT IN (:pages)")
    suspend fun deleteMoviesNotInPages(pages: List<Int>)

    @Query("SELECT * FROM movies")
    @Transaction
    suspend fun getCachedMovies(): List<Movie>


}