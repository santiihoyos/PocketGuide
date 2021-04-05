package com.santiihoyos.api_keyvalue.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.santiihoyos.api_keyvalue.SharedPreferencesKeyValueRepository
import com.santiihoyos.base_api.repository.KeyValueRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class ApiKeyValueBindsModule {

    @Binds
    abstract fun bindKeyValueRepository(repo: SharedPreferencesKeyValueRepository): KeyValueRepository
}

@Module
internal object ApiKeyValueModule {

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
}
