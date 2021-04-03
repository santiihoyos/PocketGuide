package com.santiihoyos.characters.di

import android.app.Application
import com.santiihoyos.characters.list.view.CharacterListFragment
import com.santiihoyos.data.di.DataComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        CharactersBindsModule::class,
        CharactersModule::class
    ],
    dependencies = [
        DataComponent::class
    ]
)
interface CharactersComponent {

    fun inject(characterListFragment: CharacterListFragment)

    companion object {

        lateinit var instance: CharactersComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(
            application: Application,
            dataComponent: DataComponent
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

        fun dataComponent(dataComponent: DataComponent): Builder

        fun build(): CharactersComponent
    }

}
