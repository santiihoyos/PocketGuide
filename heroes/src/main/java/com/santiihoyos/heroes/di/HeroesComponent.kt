package com.santiihoyos.heroes.di

import android.app.Application
import com.santiihoyos.api_marvel.di.ApiMarvelComponent
import com.santiihoyos.heroes.list.view.HeroListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        HeroesBindsModule::class,
        HeroesModule::class
    ],
    dependencies = [
        ApiMarvelComponent::class
    ]
)
interface HeroesComponent {

    fun inject(heroListFragment: HeroListFragment)

    companion object {

        lateinit var instance: HeroesComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(
            application: Application
        ): HeroesComponent {

            if (!this::instance.isInitialized) {

                instance = DaggerHeroesComponent.builder()
                    .application(application)
                    .apiMarvelComponent(ApiMarvelComponent.init())
                    .build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun apiMarvelComponent(apiMarvelComponent: ApiMarvelComponent): Builder

        fun build(): HeroesComponent
    }

}
