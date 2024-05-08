package com.project.api.data.remote

import android.telecom.Call.Details
import com.project.api.BuildConfig
import com.project.api.model.DetailsResponse
import com.project.api.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi{

    @GET("3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key")
        apiKey: String = BuildConfig.TDMB_API_KEY,
        @Query("language")
        language: String = "en_US",
        @Query("page")
        page: Int = 1,

    ): Response<SearchResponse>

    @GET("3/movie/{movie_id}")
    suspend fun getDetails(
        @Query("api_key")
        apiKey: String = BuildConfig.TDMB_API_KEY,
        @Query("language")
        language: String = "en_US",
        @Path("movie_id")
        id: Int

        ): Response<DetailsResponse>

    @GET("3/search/multi")
    suspend fun getSearchMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.TDMB_API_KEY,
        @Query("language")
        language: String = "en_US",
        @Query("query")
        query: String,
        @Query("page")
        page: Int = 1,
        @Query("include_adult")
        includeAdult: Boolean = false

        ): Response<SearchResponse>




}