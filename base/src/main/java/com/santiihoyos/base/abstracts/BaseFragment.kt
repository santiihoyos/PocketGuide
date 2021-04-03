package com.santiihoyos.base.abstracts

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * Common logic between view-models where T is the View Contract
 */
abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    protected abstract val viewModelProviderFactory: ViewModelProvider.Factory

    @Suppress("UNCHECKED_CAST")
    protected val viewModel: T by lazy {

        ViewModelProvider(this, viewModelProviderFactory).get(getViewModelAbstract())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    /**
     * Inject current fragment using module
     * component.
     */
    protected abstract fun inject()

    /**
     * Resolve ViewModel abstract class, necessary to inject this view
     */
    protected abstract fun getViewModelAbstract(): Class<T>

    //more common logic for fragments here...
}
