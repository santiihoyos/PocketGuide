package com.santiihoyos.api_rickandmorty.di

import com.santiihoyos.api_rickandmorty.RetrofitRickAndMortyRestRepository
import com.santiihoyos.api_rickandmorty.RickAndMortyRestRepository
import com.santiihoyos.api_rickandmorty.usecase.GetCharacterByIdUseCase
import com.santiihoyos.api_rickandmorty.usecase.GetCharacterByIdUseCaseImpl
import com.santiihoyos.api_rickandmorty.usecase.GetCharactersPagingUseCase
import com.santiihoyos.api_rickandmorty.usecase.GetCharactersPagingUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class ApiRickAndMortyBindsModule {

    @Binds
    abstract fun bindsGetCharactersPagingUseCase(
        getCharactersPagingUseCase: GetCharactersPagingUseCaseImpl
    ): GetCharactersPagingUseCase


    @Binds
    abstract fun bindsGetCharacterByIdUseCase(
        getCharacterByIdUseCase: GetCharacterByIdUseCaseImpl
    ): GetCharacterByIdUseCase
}

@Module
internal object ApiRickAndMortyModule {

    @Provides
    fun provideRestRepository(): RickAndMortyRestRepository = RetrofitRickAndMortyRestRepository.getInstance()

}
