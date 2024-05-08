package com.project.api.domain.Details

import com.project.api.data.repository.DetailsRepository
import com.project.api.model.DetailsResponse
import com.project.api.model.UIState
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor(private val detailsRepository:DetailsRepository ) {

    suspend operator fun invoke(id: Int): UIState<DetailsResponse>{
        return detailsRepository.getMoviesDetails(id)
    }
}