package com.santiihoyos.heroes.di

import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.feature.abstracts.BaseViewModel
import com.santiihoyos.base.di.ViewModelKey
import com.santiihoyos.base.di.ViewModelProviderFactory
import com.santiihoyos.heroes.list.interactor.HeroListInteractor
import com.santiihoyos.heroes.list.interactor.HeroListInteractorImpl
import com.santiihoyos.heroes.list.viewmodel.HeroListViewModel
import com.santiihoyos.heroes.list.viewmodel.HeroListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
abstract class HeroesBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(HeroListViewModel::class)
    abstract fun bindHeroListViewModelContract(
        characterListViewModel: HeroListViewModelImpl
    ): BaseViewModel

    @Binds
    abstract fun bindHeroListInteractorContract(
        characterListInteractorImpl: HeroListInteractorImpl
    ): HeroListInteractor
}

@Module
object HeroesModule {

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    @Provides
    fun provideHeroesViewModelFactory(
        providersMap: Map<Class<out BaseViewModel>, @JvmSuppressWildcards Provider<BaseViewModel>>
    ): ViewModelProvider.Factory {

        if (!HeroesModule::viewModelFactory.isInitialized) {

            viewModelFactory = ViewModelProviderFactory(providersMap)
        }

        return viewModelFactory
    }

    //All complex object creations of module characters here...
}
