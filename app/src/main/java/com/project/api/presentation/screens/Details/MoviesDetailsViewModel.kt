package com.project.api.presentation.screens.Details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.api.domain.Details.GetMoviesDetailsUseCase
import com.project.api.model.DetailsResponse
import com.project.api.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val getMoviesDetailsUseCase: GetMoviesDetailsUseCase
):ViewModel() {
    var movieDetailState: MutableStateFlow<UIState<DetailsResponse>> =
        MutableStateFlow(UIState.Loading())

    fun getMoviesDetails(id : Int) {
        viewModelScope.launch {
            when(val response = getMoviesDetailsUseCase.invoke(id)){
                is UIState.Success -> movieDetailState.value = UIState.Success(response.data)
                is UIState.Empty -> movieDetailState.value = UIState.Empty(title = response.title)
                is UIState.Error -> movieDetailState.value = UIState.Error(response.error)
                is UIState.Loading -> movieDetailState.value = UIState.Loading()
            }
        }
    }
}