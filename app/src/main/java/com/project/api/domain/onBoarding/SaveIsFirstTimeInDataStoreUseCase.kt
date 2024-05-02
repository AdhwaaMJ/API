package com.project.api.domain.onBoarding

import com.project.api.data.DataStore.MoviesAppDataStore
import javax.inject.Inject

class SaveIsFirstTimeInDataStoreUseCase @Inject constructor(
    private val dataStore: MoviesAppDataStore
) {
    suspend operator fun invoke (showTipsPage: Boolean){
        dataStore.saveOnBoardingState(ShowPerfsPage = showTipsPage)
    }

}