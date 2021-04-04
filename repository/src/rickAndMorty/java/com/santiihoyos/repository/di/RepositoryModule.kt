package com.santiihoyos.repository.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.santiihoyos.repositoryrickandmorty.RetrofitRickAndMortyRestRepository
import com.santiihoyos.repositoryrickandmorty.RickAndMortyRestRepository
import dagger.Module
import dagger.Provides

@Module
internal object RepositoryModule {

    private const val DEFAULT_PREFERENCES_NAME = "default"

    /**
     *  resolves SharedPreferences
     *
     *  @return SharedPreferences implementation
     */
    @Provides
    fun providesSharedPreferences(application: Application): SharedPreferences {

        return application.applicationContext.getSharedPreferences(
            DEFAULT_PREFERENCES_NAME, Context.MODE_PRIVATE
        )
    }

    @Provides
    fun provideRestRepository(): RickAndMortyRestRepository = RetrofitRickAndMortyRestRepository.getInstance()
}
