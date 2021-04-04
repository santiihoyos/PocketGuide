package com.santiihoyos.heroes.list.viewmodel

import com.santiihoyos.heroes.list.interactor.HeroListInteractor
import javax.inject.Inject

class HeroListViewModelImpl @Inject constructor(
    private val heroListInteractor: HeroListInteractor
): HeroListViewModel() {



}
