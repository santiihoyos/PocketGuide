package com.santiihoyos.api_marvel.di

import com.santiihoyos.api_marvel.usecase.GetCharacterByIdUseCase
import com.santiihoyos.api_marvel.usecase.GetCharactersPagingUseCase
import dagger.Component

@Component(
    modules = [ApiMarvelModule::class, ApiMarvelBindsModule::class]
)
interface ApiMarvelComponent {

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

        lateinit var instance: ApiMarvelComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(
            apiKey: String,
            privateKey: String
        ): ApiMarvelComponent {

            if (!this::instance.isInitialized) {

                ApiMarvelModule.apiKey = apiKey
                ApiMarvelModule.privateKey = privateKey
                instance = DaggerApiMarvelComponent.builder().build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        fun build(): ApiMarvelComponent
    }

}
