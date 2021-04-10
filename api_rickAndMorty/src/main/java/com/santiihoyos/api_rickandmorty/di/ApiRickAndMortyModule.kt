package com.santiihoyos.api_rickandmorty.di

import com.santiihoyos.api_rickandmorty.RickAndMortyRestRepositoryRetrofitImpl
import com.santiihoyos.api_rickandmorty.RickAndMortyRestRepository
import com.santiihoyos.api_rickandmorty.usecase.GetCharacterByIdUseCase
import com.santiihoyos.api_rickandmorty.usecase.implementation.GetCharacterByIdUseCaseImpl
import com.santiihoyos.api_rickandmorty.usecase.GetCharactersPagingUseCase
import com.santiihoyos.api_rickandmorty.usecase.implementation.GetCharactersPagingUseCaseImpl
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
    fun provideRestRepository(): RickAndMortyRestRepository = RickAndMortyRestRepositoryRetrofitImpl.getInstance()

}
