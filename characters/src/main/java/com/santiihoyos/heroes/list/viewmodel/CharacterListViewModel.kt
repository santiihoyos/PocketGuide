package com.santiihoyos.heroes.list.viewmodel

import androidx.lifecycle.MutableLiveData
import com.santiihoyos.base.abstracts.BaseViewModel
import com.santiihoyos.heroes.list.entity.Character
import com.santiihoyos.heroes.list.interactor.CharacterListInteractor

class CharacterListViewModel : BaseViewModel<CharacterListInteractor>(), CharacterListViewModelContract {

    override val heroes: MutableLiveData<List<Character>?>
        get() = MutableLiveData()

    override suspend fun loadHeroes() {

    }
}
