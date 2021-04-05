package com.santiihoyos.marvelguide.di

import android.app.Application
import com.santiihoyos.characters.di.CharactersComponent
import com.santiihoyos.marvelguide.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class
    ],
    dependencies = [
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

                val charactersComponent = CharactersComponent.init(application)

                instance = DaggerAppComponent.builder()
                    .application(application)
                    .charactersComponent(charactersComponent)
                    .build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun charactersComponent(charactersComponent: CharactersComponent): Builder

        fun build(): AppComponent
    }
}
