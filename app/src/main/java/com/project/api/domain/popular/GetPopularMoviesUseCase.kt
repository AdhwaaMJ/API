package com.project.api.domain.popular

import androidx.paging.PagingData
import com.project.api.data.repository.PopularMoviesRepository
import com.project.api.model.Results
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@Reusable
class GetPopularMoviesUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository ) {
    suspend operator fun invoke() : Flow <PagingData<Results>>{
        return popularMoviesRepository.getPopulareMovies()
    }
}