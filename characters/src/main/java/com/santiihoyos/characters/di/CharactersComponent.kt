package com.santiihoyos.characters.di

import android.app.Application
import com.santiihoyos.api_keyvalue.di.ApiKeyValueComponent
import com.santiihoyos.api_rickandmorty.di.ApiRickAndMortyComponent
import com.santiihoyos.characters.detail.view.CharacterDetailActivity
import com.santiihoyos.characters.list.view.CharacterListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        CharactersBindsModule::class,
        CharactersModule::class
    ],
    dependencies = [
        ApiRickAndMortyComponent::class,
        ApiKeyValueComponent::class
    ]
)
interface CharactersComponent {

    fun inject(characterListFragment: CharacterListFragment)

    fun inject(characterListActivity: CharacterDetailActivity)

    companion object {

        lateinit var instance: CharactersComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(
            application: Application
        ): CharactersComponent {

            if (!this::instance.isInitialized) {

                instance = DaggerCharactersComponent.builder()
                    .application(application)
                    .apiRickAndMortyComponent(ApiRickAndMortyComponent.init())
                    .apiKeyValueComponent(ApiKeyValueComponent.init(application))
                    .build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun apiRickAndMortyComponent(dataComponent: ApiRickAndMortyComponent): Builder

        fun apiKeyValueComponent(apiKeyValueComponent: ApiKeyValueComponent): Builder

        fun build(): CharactersComponent
    }

}
