package com.santiihoyos.marvelguide.di

import android.app.Application
import com.santiihoyos.characters.di.CharactersComponent
import com.santiihoyos.marvelguide.MainActivity
import com.santiihoyos.repository.di.RepositoryComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class
    ],
    dependencies = [
        RepositoryComponent::class,
        CharactersComponent::class
    ]
)
interface AppComponent {

    /**
     * Injects MainActivity classes
     *
     * @param mainActivity - MainActivity instance to inject
     */
    fun inject(mainActivity: MainActivity)

    companion object {

        lateinit var instance: AppComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @param application Application class
         * @return DataComponent implementation
         */
        fun init(
            application: Application
        ): AppComponent {

            if (!this::instance.isInitialized) {

                val dataComponent = RepositoryComponent.init(application)
                val charactersComponent = CharactersComponent.init(application, dataComponent)

                instance = DaggerAppComponent.builder()
                    .application(application)
                    .dataComponent(dataComponent)
                    .charactersComponent(charactersComponent)
                    .build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        fun dataComponent(dataComponent: RepositoryComponent): Builder

        fun charactersComponent(charactersComponent: CharactersComponent): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
