package com.santiihoyos.api_rickandmorty.di

import com.santiihoyos.api_rickandmorty.RetrofitRickAndMortyRestRepository
import com.santiihoyos.api_rickandmorty.RickAndMortyRestRepository
import dagger.Module
import dagger.Provides

@Module
object ApiRickAndMortyModule {

    @Provides
    fun provideRestRepository(): RickAndMortyRestRepository = RetrofitRickAndMortyRestRepository.getInstance()

}
