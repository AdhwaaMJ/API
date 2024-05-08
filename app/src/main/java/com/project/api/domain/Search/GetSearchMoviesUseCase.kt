package com.project.api.domain.Search

import androidx.paging.PagingData
import com.project.api.data.repository.SearchRepository
import com.project.api.model.Results
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchMoviesUseCase @Inject constructor( private val searchRepository: SearchRepository )
{
    suspend fun invoke(query: String): Flow <PagingData<Results>>{
        return searchRepository.getSearchMovies(query)
    }
}