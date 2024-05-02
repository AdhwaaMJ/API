package com.project.api.data.DataStore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


const val PREFERENCE_NAME = "is_first_time"
val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(name = "settings")

class MoviesAppDataStore @Inject constructor(context: Context) {
    private val onBoardingScreenKey = booleanPreferencesKey(name = PREFERENCE_NAME)

    private val dataStore = context.dataStore

    suspend fun saveOnBoardingState(ShowPerfsPage: Boolean) {

        dataStore.edit {
            it[onBoardingScreenKey] = ShowPerfsPage
        }
    }

     fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.map {
            it[onBoardingScreenKey] ?: false
        }

    }
}