package com.santiihoyos.heroes.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.feature.abstracts.BaseFragment
import com.santiihoyos.heroes.di.HeroesComponent
import com.santiihoyos.heroes.R
import com.santiihoyos.heroes.list.viewmodel.HeroListViewModel
import javax.inject.Inject

class HeroListFragment : BaseFragment<HeroListViewModel>() {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun inject() = HeroesComponent.instance.inject(this)

    override fun getViewModelAbstract(): Class<HeroListViewModel> = HeroListViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_heroes_list, container, false)
}
