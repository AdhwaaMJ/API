package com.project.api.data.remote

import android.telecom.Call.Details
import com.project.api.BuildConfig
import com.project.api.model.DetailsResponse
import com.project.api.model.SearchResponse
import com.project.api.model.UserAccount
import com.project.api.model.UserTokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
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
        @Path("movie_id")
        id: Int,
        @Query("api_key")
        apiKey: String = BuildConfig.TDMB_API_KEY,
        @Query("language")
        language: String = "en_US",


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

    @GET("3/authentication/token/new")
    suspend fun getUserToken(
        @Query("api_key")
        apiKey: String = BuildConfig.TDMB_API_KEY,
        ) : Response<UserTokenResponse>

    @POST("3/authentication/session/new")
    suspend fun getSessionId(
        @Query("api_key")
        apiKey: String = BuildConfig.TDMB_API_KEY,
        @Query("request_token")
        requestToken:String
    ): Response<UserTokenResponse>

    @GET("3/account/")
    suspend fun getUserAccount(
        @Query("api_key")
        apiKey: String = BuildConfig.TDMB_API_KEY,
        @Query("session_id")
        sessionId:String
    ) : Response<UserAccount>







}