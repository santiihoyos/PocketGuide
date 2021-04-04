package com.santiihoyos.repository.di

import android.app.Application
import com.santiihoyos.repositorymarvel.MarvelRestRepository
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [RepositoryModule::class, RepositoryBindsModule::class]
)
interface RepositoryComponent{

    /**
     * Exposes to other components RestRepository implementation.
     *
     * @return RestRepository implementation
     */
    fun getRestRepository(): MarvelRestRepository

    companion object {

        lateinit var instance: RepositoryComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(
            application: Application
        ): RepositoryComponent {

            if (!this::instance.isInitialized) {

                instance = DaggerRepositoryComponent.builder()
                    .application(application)
                    .build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): RepositoryComponent
    }

}
