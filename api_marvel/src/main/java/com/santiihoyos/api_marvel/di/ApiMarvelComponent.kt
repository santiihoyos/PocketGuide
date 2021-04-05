package com.santiihoyos.api_marvel.di

import dagger.Component

@Component(
    modules = [ApiRickAndMortyModule::class]
)
interface ApiMarvelComponent{


    companion object {

        lateinit var instance: ApiMarvelComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(): ApiMarvelComponent {

            if (!this::instance.isInitialized) {

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
