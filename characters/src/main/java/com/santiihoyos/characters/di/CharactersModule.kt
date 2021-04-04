package com.santiihoyos.characters.di

import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.feature.abstracts.BaseViewModel
import com.santiihoyos.base.di.ViewModelKey
import com.santiihoyos.base.di.ViewModelProviderFactory
import com.santiihoyos.base_repository.Mapper
import com.santiihoyos.characters.detail.interactor.CharacterDetailInteractor
import com.santiihoyos.characters.detail.interactor.CharacterDetailInteractorImpl
import com.santiihoyos.characters.detail.viewModel.CharacterDetailViewModel
import com.santiihoyos.characters.detail.viewModel.CharacterDetailViewModelImpl
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.interactor.CharacterListInteractorImpl
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import com.santiihoyos.characters.list.viewmodel.CharacterListViewModel
import com.santiihoyos.characters.list.viewmodel.CharacterListViewModelImpl
import com.santiihoyos.characters.entity.mappers.CharacterMapper
import com.santiihoyos.repositoryrickandmorty.response.CharacterResponse
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
abstract class CharactersBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    abstract fun bindCharacterListViewModelContract(
        characterListViewModel: CharacterListViewModelImpl
    ): BaseViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    abstract fun bindCharacterDetailViewModelContract(
        characterDetailViewModel: CharacterDetailViewModelImpl
    ): BaseViewModel

    @Binds
    abstract fun bindCharacterListInteractorContract(
        characterListInteractorImpl: CharacterListInteractorImpl
    ): CharacterListInteractor

    @Binds
    abstract fun bindCharacterDetailInteractorContract(
        characterListInteractorImpl: CharacterDetailInteractorImpl
    ): CharacterDetailInteractor

    @Binds
    abstract fun bindCharacterMapper(
        characterMapper: CharacterMapper
    ): Mapper<CharacterResponse, Character>
}

@Module
object CharactersModule {

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    @Provides
    fun provideCharactersViewModelFactory(
        providersMap: Map<Class<out BaseViewModel>, @JvmSuppressWildcards Provider<BaseViewModel>>
    ): ViewModelProvider.Factory {

        if (!::viewModelFactory.isInitialized) {

            viewModelFactory = ViewModelProviderFactory(providersMap)
        }

        return viewModelFactory
    }

    //All complex object creations of module characters here...
}
