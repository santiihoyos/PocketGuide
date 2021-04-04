package com.santiihoyos.characters.di

import android.app.Application
import com.santiihoyos.heroes.di.HeroesBindsModule
import com.santiihoyos.heroes.di.HeroesModule
import com.santiihoyos.heroes.list.view.HeroListFragment
import com.santiihoyos.repository.di.RepositoryComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        HeroesBindsModule::class,
        HeroesModule::class
    ],
    dependencies = [
        RepositoryComponent::class
    ]
)
interface HeroesComponent {

    fun inject(heroListFragment: HeroListFragment)

    //fun inject(characterListActivity: CharacterDetailActivity)

    companion object {

        lateinit var instance: HeroesComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(
            application: Application,
            dataComponent: RepositoryComponent
        ): HeroesComponent {

            if (!this::instance.isInitialized) {

                instance = DaggerHeroesComponent.builder()
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

        fun build(): HeroesComponent
    }

}
