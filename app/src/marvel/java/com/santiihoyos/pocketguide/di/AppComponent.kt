package com.santiihoyos.pocketguide.di

import android.app.Application
import com.santiihoyos.characters.di.CharactersComponent
import com.santiihoyos.marvelguide.BuildConfig
import com.santiihoyos.pocketguide.main.view.MainActivity
import com.santiihoyos.pocketguide.dummy.view.DummyFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        AppModuleBinds::class
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

    /**
     * Injects Dummy fragment
     *
     * @param dummyFragment - DummyFragment instance to inject
     */
    fun inject(dummyFragment: DummyFragment)

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

                val charactersComponent = CharactersComponent.init(
                    application,
                    BuildConfig.API_KEY,
                    BuildConfig.PRIVATE_KEY
                )

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
