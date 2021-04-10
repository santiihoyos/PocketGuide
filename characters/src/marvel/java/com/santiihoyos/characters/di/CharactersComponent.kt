package com.santiihoyos.characters.di

import android.content.Context
import com.santiihoyos.api_keyvalue.di.ApiKeyValueComponent
import com.santiihoyos.api_marvel.di.ApiMarvelComponent
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
        ApiMarvelComponent::class,
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
            context: Context,
            apiKey: String,
            privateKey: String
        ): CharactersComponent {

            if (!this::instance.isInitialized) {

                instance = DaggerCharactersComponent.builder()
                    .application(context)
                    .apiRickAndMortyComponent(ApiMarvelComponent.init(apiKey, privateKey))
                    .apiKeyValueComponent(ApiKeyValueComponent.init(context))
                    .build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun apiRickAndMortyComponent(dataComponent: ApiMarvelComponent): Builder

        fun apiKeyValueComponent(apiKeyValueComponent: ApiKeyValueComponent): Builder

        fun build(): CharactersComponent
    }

}
