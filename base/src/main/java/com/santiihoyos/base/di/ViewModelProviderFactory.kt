package com.santiihoyos.base.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.abstracts.BaseInteractor
import com.santiihoyos.base.abstracts.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider

/**
 * Wrap to use ViewModel Google implementation with Dagger2
 */
class ViewModelProviderFactory @Inject constructor(
    private val creators: Map<Class<out BaseViewModel>, @JvmSuppressWildcards Provider<BaseViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST", "TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
    override fun <T : ViewModel> create(modelClass: Class<T>) = creators[modelClass]?.get() as T
}
