package com.santiihoyos.marvelguide.di

import android.app.Application
import com.santiihoyos.data.di.DataComponent
import com.santiihoyos.heroes.list.di.CharactersComponent
import com.santiihoyos.marvelguide.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class
    ],
    dependencies = [
        DataComponent::class,
        CharactersComponent::class
    ]
)
interface AppComponent {

    /**
     * Injects MainActivity classes
     */
    fun inject(mainActivity: MainActivity)

    //region exposed dependencies

    fun getApplicationContext(application: Application)

    //endregion

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

                val dataComponent = DataComponent.init(application)
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

        fun dataComponent(dataComponent: DataComponent): Builder

        fun charactersComponent(charactersComponent: CharactersComponent): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
