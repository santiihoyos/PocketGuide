package com.santiihoyos.repository.di

import com.santiihoyos.base_repository.repository.KeyValueRepository
import com.santiihoyos.repository.repository.SharedPreferencesKeyValueRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class RepositoryBindsModule {

    @Binds
    abstract fun bindKeyValueRepository(repo: SharedPreferencesKeyValueRepository): KeyValueRepository
}
