package com.santiihoyos.api_rickandmorty.di

import com.santiihoyos.api_rickandmorty.usecase.GetCharactersPagingUseCase
import dagger.Component

@Component(
    modules = [ApiRickAndMortyModule::class]
)
interface ApiRickAndMortyComponent{

    /**
     * Exposes to other components RestRepository implementation.
     *
     * @return RestRepository implementation
     */
    fun getRestRepository(): GetCharactersPagingUseCase

    companion object {

        lateinit var instance: ApiRickAndMortyComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(): ApiRickAndMortyComponent {

            if (!this::instance.isInitialized) {

                instance = DaggerApiRickAndMortyComponent.builder().build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        fun build(): ApiRickAndMortyComponent
    }

}
