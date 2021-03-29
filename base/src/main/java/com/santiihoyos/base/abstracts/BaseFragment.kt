package com.santiihoyos.base.abstracts

import androidx.fragment.app.Fragment
import com.santiihoyos.base.contracts.BaseViewModelContract

/**
 * Common logic between view-models where T is the View Contract
 */
open class BaseFragment<T : BaseViewModelContract> : Fragment() {

    //TODO: all common logic for Fragments
    open lateinit var viewModel: T
}
