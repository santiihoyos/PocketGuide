package com.santiihoyos.api_marvel.di

import com.santiihoyos.api_marvel.MarvelRestRepository
import com.santiihoyos.api_marvel.MarvelRestRepositoryRetrofitImpl
import com.santiihoyos.api_marvel.usecase.GetCharacterByIdUseCase
import com.santiihoyos.api_marvel.usecase.GetCharactersPagingUseCase
import com.santiihoyos.api_marvel.usecase.implementation.GetCharacterByIdUseCaseImpl
import com.santiihoyos.api_marvel.usecase.implementation.GetCharactersPagingUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class ApiMarvelBindsModule {

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
internal object ApiMarvelModule {

    var apiKey: String = ""

    var baseUrl: String = ""

    var privateKey: String = ""

    @Provides
    fun provideRestRepository(): MarvelRestRepository = MarvelRestRepositoryRetrofitImpl.getInstance(apiKey, baseUrl, privateKey)
}
