package com.project.api.data.repository
import com.project.api.data.remote.MovieApi
import com.project.api.model.DetailsResponse
import com.project.api.model.UIState
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    val movieApi: MovieApi
) {
    suspend fun getMoviesDetails(id: Int): UIState<DetailsResponse> {
        try {
            val response = movieApi.getDetails(id = id)
            if (response.isSuccessful && response.body() != null) {
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception) {
            return UIState.Error(e.message.toString())
        }
    }
}

