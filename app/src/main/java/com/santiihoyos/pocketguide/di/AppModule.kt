package com.santiihoyos.pocketguide.di

import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.di.ViewModelKey
import com.santiihoyos.base.di.ViewModelProviderFactory
import com.santiihoyos.base.feature.abstracts.BaseViewModel
import com.santiihoyos.pocketguide.main.interactor.MainInteractor
import com.santiihoyos.pocketguide.main.interactor.MainInteractorImpl
import com.santiihoyos.pocketguide.main.viewmodel.MainViewModel
import com.santiihoyos.pocketguide.main.viewmodel.MainViewModelImpl
import com.santiihoyos.pocketguide.dummy.interactor.DummyInteractor
import com.santiihoyos.pocketguide.dummy.interactor.DummyInteractorImpl
import com.santiihoyos.pocketguide.dummy.viewmodel.DummyViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
abstract class AppModuleBinds {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModelContract(
        characterListViewModel: MainViewModelImpl
    ): BaseViewModel


    @Binds
    abstract fun bindMainInteractorContract(
        characterListViewModel: MainInteractorImpl
    ): MainInteractor

    @Binds
    @IntoMap
    @ViewModelKey(DummyViewModel::class)
    abstract fun bindDummyViewModelContract(
        characterListViewModel: MainViewModelImpl
    ): BaseViewModel


    @Binds
    abstract fun bindDummyInteractorContract(
        dummyInteractorImpl: DummyInteractorImpl
    ): DummyInteractor
}


@Module
object AppModule {

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    @Provides
    fun provideAppViewModelFactory(
        providersMap: Map<Class<out BaseViewModel>, @JvmSuppressWildcards Provider<BaseViewModel>>
    ): ViewModelProvider.Factory {

        if (!AppModule::viewModelFactory.isInitialized) {

            viewModelFactory = ViewModelProviderFactory(providersMap)
        }

        return viewModelFactory
    }
}
