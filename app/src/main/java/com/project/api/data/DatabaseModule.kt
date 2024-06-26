package com.project.api.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase{
        return MovieDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideMoviesDao(database: MovieDatabase) = database.moviesDao()
}