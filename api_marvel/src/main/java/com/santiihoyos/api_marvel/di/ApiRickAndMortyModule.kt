package com.santiihoyos.api_marvel.di

import com.santiihoyos.api_marvel.MarvelRestRepository
import com.santiihoyos.api_marvel.RetrofitMarvelRestRepository
import dagger.Module
import dagger.Provides

@Module
object ApiRickAndMortyModule {

    @Provides
    fun provideRestRepository(): MarvelRestRepository = RetrofitMarvelRestRepository.getInstance()

}
