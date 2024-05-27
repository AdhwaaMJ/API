package com.project.api.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.api.data.moviesDao
import com.project.api.data.paging.MoviePagingSource
import com.project.api.data.remote.MovieApi
import com.project.api.model.Results
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    val movieApi: MovieApi,
    val moviesDao: moviesDao
) {
    suspend fun getSearchMovies(query: String): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviePagingSource(movieApi,true, query, moviesDao)
            }
        ).flow
    }

}