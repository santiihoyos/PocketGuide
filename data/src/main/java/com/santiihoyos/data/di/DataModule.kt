package com.santiihoyos.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.santiihoyos.data.repository.KeyValueRepository
import com.santiihoyos.data.repository.RestRepository
import com.santiihoyos.data.repository.implementation.RetrofitRestRepository
import com.santiihoyos.data.repository.implementation.SharedPreferencesKeyValueRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataBindsModule {

    @Binds
    abstract fun bindKeyValueRepository(repo: SharedPreferencesKeyValueRepository): KeyValueRepository
}

@Module
object DataModule {

    private const val DEFAULT_PREFERENCES_NAME = "default"

    /**
     *  resolves SharedPreferences
     *
     *  @return SharedPreferences implementation
     */
    @Provides
    fun providesSharedPreferences(application: Application): SharedPreferences {

        return application.applicationContext.getSharedPreferences(
            DEFAULT_PREFERENCES_NAME, Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideRestRepository(): RestRepository = RetrofitRestRepository.getInstance()
}
