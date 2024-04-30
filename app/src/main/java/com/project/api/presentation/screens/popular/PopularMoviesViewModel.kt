package com.project.api.presentation.screens.popular

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.api.domain.popular.GetPopularMoviesUseCase
import com.project.api.model.SearchResponse
import com.project.api.model.UIState
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): ViewModel() {

    var popularMoviesState: MutableState<UIState<SearchResponse>> = mutableStateOf(UIState.Loading())

    init {
        getArtWorks()
    }

    private fun getArtWorks(){
        viewModelScope.launch {
            when(val response = getPopularMoviesUseCase.invoke()){
                is UIState.Success -> popularMoviesState.value = UIState.Success(response.data)
                is UIState.Error -> popularMoviesState.value = UIState.Error(response.error)
                is UIState.Empty -> popularMoviesState.value = UIState.Empty(title = response.message)
                is UIState.Loading -> popularMoviesState.value = UIState.Loading()
            }
        }
    }
}