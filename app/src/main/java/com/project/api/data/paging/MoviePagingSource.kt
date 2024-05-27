package com.project.api.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.api.data.Movie
import com.project.api.data.moviesDao
import com.project.api.data.remote.MovieApi
import com.project.api.model.Results

class MoviePagingSource(
    private val movieApi: MovieApi,
    private val isSearchEndPoint: Boolean,
    private val searchQuery: String? = null,
    private val moviesDao : moviesDao
) : PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {

        return try {
            val cachedMovies = moviesDao.getCachedMovies()
            val currentPage = params.key ?: 1

            try {

                val movies = if (isSearchEndPoint)
                    movieApi.getSearchMovies(
                        page = currentPage,
                        query = searchQuery.orEmpty(),
                    )
                else
                    movieApi.getUpcoming(
                        page = currentPage
                    )
                val moviesList = movies.body()?.results?.map {
                    Movie(
                        id = it.id ?: -1,
                        title = it.title.orEmpty(),
                        overview = it.overview.orEmpty(),
                        posterPath = it.posterPath.orEmpty(),
                        backdropPath = it.backdropPath.orEmpty(),
                        page = currentPage
                    )
                }
                moviesDao.insertMovies(moviesList.orEmpty())

                val pagesToKeep = listOf(currentPage - 1, currentPage, currentPage + 1)

                moviesDao.deleteMoviesNotInPages(
                    pagesToKeep
                )

                LoadResult.Page(
                    data = movies.body()?.results.orEmpty(),
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (movies.body()?.results?.isEmpty() == true) null else movies.body()?.page!! + 1

                )

            } catch (exception: Exception) {
                LoadResult.Page(
                    data = cachedMovies.map {
                        Results(
                            adult = false,
                            backdropPath = it.backdropPath,
                            id = it.id,
                            title = it.title,
                            overview = it.overview,
                            posterPath = it.posterPath,
                        )
                    },
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }
}


