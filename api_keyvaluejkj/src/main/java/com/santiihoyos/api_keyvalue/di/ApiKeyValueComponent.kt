package com.santiihoyos.api_keyvalue.di

import android.content.Context
import com.santiihoyos.base_api.repository.KeyValueRepository
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ApiKeyValueModule::class, ApiKeyValueBindsModule::class],
)
interface ApiKeyValueComponent{

    /**
     * Exposes to other components [KeyValueRepository] implementation.
     *
     * @return KeyValueRepository implementation
     */
    fun getKeyValueRepository(): KeyValueRepository

    companion object {

        lateinit var instance: ApiKeyValueComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(
            context: Context
        ): ApiKeyValueComponent {

            if (!this::instance.isInitialized) {

                instance = DaggerApiKeyValueComponent.builder()
                    .context(context)
                    .build()
            }

            return instance
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApiKeyValueComponent
    }

}
