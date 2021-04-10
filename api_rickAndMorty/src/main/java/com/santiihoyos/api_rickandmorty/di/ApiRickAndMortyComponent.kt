package com.santiihoyos.api_rickandmorty.di

import com.santiihoyos.api_rickandmorty.usecase.GetCharacterByIdUseCase
import com.santiihoyos.api_rickandmorty.usecase.GetCharactersPagingUseCase
import dagger.Component

@Component(
    modules = [ApiRickAndMortyModule::class, ApiRickAndMortyBindsModule::class]
)
interface ApiRickAndMortyComponent {

    /**
     * Exposes to other components UseCase implementation.
     * for get characters paginated list.
     *
     * @return GetCharactersPagingUseCase implementation
     */
    fun getCharactersPagingUseCase(): GetCharactersPagingUseCase

    /**
     * Exposes to other components UseCase implementation.
     * for get character by id.
     *
     * @return GetCharacterByIdUseCase implementation
     */
    fun getCharacterByIdUseCase(): GetCharacterByIdUseCase

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
