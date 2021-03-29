package com.santiihoyos.data.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DataModule::class, DataBindsModule::class]
)
interface DataComponent {

    companion object {

        lateinit var instance: DataComponent

        /**
         * Wrapper to build DataComponent instance
         *
         * @return DataComponent implementation
         */
        fun init(
            application: Application
        ): DataComponent {

            if (!this::instance.isInitialized) {

                instance = DaggerDataComponent.builder()
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

        fun build(): DataComponent
    }

}
