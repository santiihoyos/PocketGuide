package com.santiihoyos.marvelguide.di

import android.app.Application
import com.santiihoyos.heroes.di.HeroesComponent
import com.santiihoyos.marvelguide.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class
    ],
    dependencies = [
        HeroesComponent::class
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

                instance = DaggerAppComponent.builder()
                    .application(application)
                    .heroesComponent(HeroesComponent.init(application))
                    .build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun heroesComponent(charactersComponent: HeroesComponent): Builder

        fun build(): AppComponent
    }
}
