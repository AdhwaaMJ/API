package com.project.api.data.di

import android.content.Context
import com.project.api.data.DataStore.MoviesAppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreOperation(
        @ApplicationContext context: Context
    ) = MoviesAppDataStore(context = context)

}


