package com.santiihoyos.characters.di

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.abstracts.BaseViewModel
import com.santiihoyos.base.di.ViewModelKey
import com.santiihoyos.base.di.ViewModelProviderFactory
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.interactor.CharacterListInteractorImpl
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import com.santiihoyos.characters.list.viewmodel.CharacterListViewModel
import com.santiihoyos.characters.list.viewmodel.CharacterListViewModelImpl
import com.santiihoyos.characters.mappers.CharacterMapper
import com.santiihoyos.data.Mapper
import com.santiihoyos.data.response.CharacterResponse
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
    abstract fun bindCharacterListInteractorContract(
        characterListInteractorImpl: CharacterListInteractorImpl
    ): CharacterListInteractor

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

        Log.i("MAPA:", providersMap.toString())

        if (!::viewModelFactory.isInitialized) {

            viewModelFactory = ViewModelProviderFactory(providersMap)
        }

        return viewModelFactory
    }

    //All complex object creations of module characters here...
}
