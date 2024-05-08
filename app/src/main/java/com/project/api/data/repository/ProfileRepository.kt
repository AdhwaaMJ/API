package com.project.api.data.repository

import com.project.api.data.remote.MovieApi
import com.project.api.model.UIState
import com.project.api.model.UserAccount
import com.project.api.model.UserTokenResponse
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    val movieApi: MovieApi
) {
    suspend fun getUserToken():UIState<UserTokenResponse>{
        try {
            val response = movieApi.getUserToken()
            if (response.isSuccessful && response.body() !=null){
                return UIState.Success(response.body())
            }else{
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }

    suspend fun getSessionId(requestToken: String):UIState<UserTokenResponse>{
        try {
            val response = movieApi.getSessionId(requestToken = requestToken)
            if (response.isSuccessful && response.body() !=null){
                return UIState.Success(response.body())
            }else{
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }

    suspend fun getUserAccount(sessionId: String):UIState<UserAccount>{
        try {
            val response = movieApi.getUserAccount(sessionId = sessionId)
            if (response.isSuccessful && response.body() !=null){
                return UIState.Success(response.body())
            }else{
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }


}