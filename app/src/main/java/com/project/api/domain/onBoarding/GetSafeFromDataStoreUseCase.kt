package com.project.api.domain.onBoarding

import com.project.api.data.DataStore.MoviesAppDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetSafeFromDataStoreUseCase @Inject constructor(
    private val dataStore: MoviesAppDataStore
) {
    operator fun invoke() : Flow<Boolean>{
        return  dataStore.readOnBoardingState()
    }
}