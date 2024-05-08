package com.project.api.presentation.screens.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.api.domain.Search.GetSearchMoviesUseCase
import com.project.api.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase
) :ViewModel() {
    var moviesState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())

    fun searchInMovies(query: String){
        viewModelScope.launch {
            getSearchMoviesUseCase.invoke(query).distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collectIndexed { _, value ->
                    moviesState.value = value

                }
        }
    }


}