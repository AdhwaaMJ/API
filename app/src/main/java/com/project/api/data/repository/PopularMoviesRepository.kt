package com.project.api.data.repository

import com.project.api.data.remote.MovieApi
import com.project.api.model.SearchResponse
import com.project.api.model.UIState
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(
    val movieApi: MovieApi
) {
    suspend fun getPopularMovies(): UIState<SearchResponse>{
        try {
            val response = movieApi.getUpcoming()
            if (response.isSuccessful && response.body() !=null){
                return UIState.Success(response.body())
            }else{
                return UIState.Empty(message = response.message().toString())
            }
        }  catch (e: Exception){
            return  UIState.Error(e.message.toString())
        }
    }

}