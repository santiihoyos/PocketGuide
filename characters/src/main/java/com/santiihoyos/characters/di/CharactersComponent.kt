package com.santiihoyos.characters.di

import android.app.Application
import com.santiihoyos.characters.detail.view.CharacterDetailActivity
import com.santiihoyos.characters.list.view.CharacterListFragment
import com.santiihoyos.repository.di.RepositoryComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        CharactersBindsModule::class,
        CharactersModule::class
    ],
    dependencies = [
        RepositoryComponent::class
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
            application: Application,
            dataComponent: RepositoryComponent
        ): CharactersComponent {

            if (!this::instance.isInitialized) {

                instance = DaggerCharactersComponent.builder()
                    .application(application)
                    .dataComponent(dataComponent)
                    .build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun dataComponent(dataComponent: RepositoryComponent): Builder

        fun build(): CharactersComponent
    }

}
