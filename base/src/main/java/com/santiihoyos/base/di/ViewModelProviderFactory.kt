package com.santiihoyos.base.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.feature.abstracts.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider

/**
 * Wrap to use ViewModel Google implementation with Dagger2
 */
class ViewModelProviderFactory @Inject constructor(
    private val creators: Map<Class<out BaseViewModel>, @JvmSuppressWildcards Provider<BaseViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return creators[modelClass as Class<out BaseViewModel>]?.get() as T
    }
}
