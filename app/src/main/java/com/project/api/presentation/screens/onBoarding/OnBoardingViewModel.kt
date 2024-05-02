package com.project.api.presentation.screens.onBoarding

import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.api.domain.onBoarding.GetSafeFromDataStoreUseCase
import com.project.api.domain.onBoarding.SaveIsFirstTimeInDataStoreUseCase
import com.project.api.presentation.navigation.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveIsFirstTimeInDataStoreUseCase: SaveIsFirstTimeInDataStoreUseCase,
    private val getSafeFromDataStoreUseCase: GetSafeFromDataStoreUseCase

): ViewModel(){
    val onBoardingCompleted = MutableStateFlow(false)
    var startDestination: String = AppScreen.OnboardingScreen.rout

    init {
        getOnBordingState()
    }

    private fun getOnBordingState(){
        viewModelScope.launch {
            onBoardingCompleted.value = getSafeFromDataStoreUseCase().stateIn(viewModelScope).value
            startDestination =
                if (onBoardingCompleted.value) AppScreen.MainScreen.rout else AppScreen.OnboardingScreen.rout
        }
    }

    fun saveOnBoardingState(showBoardingPage: Boolean){
        viewModelScope.launch(Dispatchers.IO){
            saveIsFirstTimeInDataStoreUseCase(showTipsPage = showBoardingPage )
        }
    }

}