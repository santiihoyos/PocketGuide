package com.santiihoyos.base.abstracts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    /**
     * Flag to inform when viewModel is bussy
     */
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    //TODO: All common logic between modules.

}
