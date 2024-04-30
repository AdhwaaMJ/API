package com.project.api.data.remote

import com.project.api.BuildConfig
import com.project.api.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
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

}