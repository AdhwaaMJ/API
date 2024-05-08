package com.project.api.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.api.data.paging.MoviePagingSource
import com.project.api.data.remote.MovieApi
import com.project.api.model.Results
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(
    val movieApi: MovieApi
) {
//    suspend fun getPopularMovies(): UIState<SearchResponse>{
//        try {
//            val response = movieApi.getUpcoming()
//            if (response.isSuccessful && response.body() !=null){
//                return UIState.Success(response.body())
//            }else{
//                return UIState.Empty(message = response.message().toString())
//            }
//        }  catch (e: Exception){
//            return  UIState.Error(e.message.toString())
//        }
//    }


    suspend fun getPopulareMovies():Flow<PagingData<Results>>{
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(movieApi,false)
            }
        ).flow
    }
}
